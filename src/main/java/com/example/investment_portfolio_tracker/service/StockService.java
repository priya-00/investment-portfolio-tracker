package com.example.investment_portfolio_tracker.service;

import com.example.investment_portfolio_tracker.dto.StockDto;
import com.example.investment_portfolio_tracker.externalClient.StocksFeignClient;
import com.example.investment_portfolio_tracker.model.Stock;
import com.example.investment_portfolio_tracker.model.StockResponse;
import com.example.investment_portfolio_tracker.repository.PortfolioRepository;
import com.example.investment_portfolio_tracker.repository.StockRepository;
import com.example.investment_portfolio_tracker.util.StockHelper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StockService {

    private final StocksFeignClient client;

    @Value("${stock.api.key}")
    private String apiKey;

    private final StockRepository stockRepository;
    private final PortfolioRepository portfolioRepository;
    private final StockHelper stockHelper;

    // get stock price
    public StockResponse getStockPrice(String ticker) {
        StockResponse stockPrice = client.getStockPrice(ticker, apiKey);
        log.info("Stock price = " + stockPrice);
        return stockPrice;
    }

    // create
    public Stock createNewAsset(Long portfolioId, StockDto stockDto) {
        log.info("Creating new asset with name: {}", stockDto.getName() );
        String ticker = stockHelper.getStockTicker(stockDto.getName());
        StockResponse stockData = getStockPrice(ticker);
        double totalValue = stockDto.getQuantity() * stockData.getPrice();
        Stock stock = new Stock();
        stock.setName(stockData.getName());
        stock.setCurrency(stockData.getCurrency());
        stock.setExchange(stockData.getExchange());
        stock.setQuantity(stockDto.getQuantity());
        stock.setTicker(stockData.getTicker());
        stock.setValue(totalValue);
        // TODO: get userId from request and setPortfolioUser for stock object
        stock.setPortfolio(portfolioRepository.getReferenceById(portfolioId));
        log.info("Stock object being saved: {}", stock);
        return stockRepository.save(stock);
    }

    // update
    public Stock updateAssetById(Long assetId, Stock stock) {
        if(stock.getId() == null) {
            stock.setId(assetId);
        }
        log.info("Updating asset with ID: {}", assetId);
        return stockRepository.save(stock);
    }

    // get
    public Stock getAssetById(Long assetId) {
        log.info("Fetching asset with ID: {}", assetId);
        return stockRepository.findById(assetId).orElseThrow(() -> new EntityNotFoundException("Asset not found with ID: " + assetId));
    }

    // delete
    public void deleteAssetById(Long assetId) {
        log.info("Deleting asset with ID: {}", assetId);
        stockRepository.deleteById(assetId);
    }
}
