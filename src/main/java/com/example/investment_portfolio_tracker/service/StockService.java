package com.example.investment_portfolio_tracker.service;

import com.example.investment_portfolio_tracker.externalClient.StocksFeignClient;
import com.example.investment_portfolio_tracker.model.Stock;
import com.example.investment_portfolio_tracker.repository.StockRepository;
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

    StockRepository stockRepository;

    // get stock price
    public String getStockPrice() {
        String stockPrice = client.getStockPrice("AAPL", apiKey);
        log.info("Stock price = " + stockPrice);
        return stockPrice;
    }

    // create
    public Stock createNewAsset(Stock stock) {
        log.info("Creating new asset with name: {}", stock.getName() );
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

    // get all
    public List<Stock> getAllAssetsOfUser(Long userId) {
        log.info("Fetching all assets in DB for user with ID: {}", userId);
        return stockRepository.findAllByPortfolioUserId(userId);
    }

    // delete
    public void deleteAssetById(Long assetId) {
        log.info("Deleting asset with ID: {}", assetId);
        stockRepository.deleteById(assetId);
    }
}
