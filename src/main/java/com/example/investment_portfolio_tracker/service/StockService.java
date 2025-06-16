package com.example.investment_portfolio_tracker.service;

import com.example.investment_portfolio_tracker.dto.StockDto;
import com.example.investment_portfolio_tracker.exception.StockNotFoundException;
import com.example.investment_portfolio_tracker.externalClient.StocksFeignClient;
import com.example.investment_portfolio_tracker.model.Portfolio;
import com.example.investment_portfolio_tracker.model.Stock;
import com.example.investment_portfolio_tracker.model.StockResponse;
import com.example.investment_portfolio_tracker.repository.PortfolioRepository;
import com.example.investment_portfolio_tracker.repository.StockRepository;
import com.example.investment_portfolio_tracker.util.StockHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StockService {

    private final StocksFeignClient client;

    @Value("${stock.api.key}")
    private String apiKey;

    @Autowired
    private final StockRepository stockRepository;
    @Autowired
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
        stock.setPortfolio(portfolioRepository.getReferenceById(portfolioId));
        return stockRepository.save(stock);
    }

    // update
    public ResponseEntity<StockDto> updateStockById(Long stockId, StockDto stockDto) {
        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new StockNotFoundException("Stock not found. Please check the stock ID."));

        Portfolio portfolio = stock.getPortfolio();

        StockResponse stockData = getStockPrice(stock.getTicker());
        double totalValue = stockDto.getQuantity() * stockData.getPrice();

        stock.setName(stockData.getName());
        stock.setCurrency(stockData.getCurrency());
        stock.setExchange(stockData.getExchange());
        stock.setQuantity(stockDto.getQuantity());
        stock.setTicker(stockData.getTicker());
        stock.setValue(totalValue);
        stock.setPortfolio(portfolio);

        stockRepository.save(stock);

        log.info("Updating stock with ID: {}", stockId);

        return ResponseEntity.ok(stockDto);
    }

    // get all for given portfolio
    public List<Stock> getStockByPortfolioId(Long portfolioId) {
        log.info("Fetching all stocks for portfolio with ID: {}", portfolioId);
        return stockRepository.findAllByPortfolioId(portfolioId);
    }

    // delete
    public void deleteStockById(Long stockId) {
        log.info("Deleting stock with ID: {}", stockId);
        stockRepository.deleteById(stockId);
    }
}
