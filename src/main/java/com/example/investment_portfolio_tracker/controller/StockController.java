package com.example.investment_portfolio_tracker.controller;

import com.example.investment_portfolio_tracker.model.Stock;
import com.example.investment_portfolio_tracker.service.StockService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
@AllArgsConstructor
public class StockController {

    StockService stockService;

    // get stock price
    @GetMapping("/price")
    public String getStockPrice() {
        return stockService.getStockPrice();
    }

    // create
    @PostMapping
    public Stock createNewAsset(Stock stock) {
        return stockService.createNewAsset(stock);
    }

    // update
    @PutMapping("/{assetId}")
    public Stock updateAssetById(@PathVariable Long assetId, @RequestBody Stock stock) {
        return stockService.updateAssetById(assetId, stock);
    }

    // get
    @GetMapping("/{assetId}")
    public Stock getAssetById(@PathVariable Long assetId) {
        return stockService.getAssetById(assetId);
    }

    // get all
    @GetMapping("/user/{userId}")
    public List<Stock> getAllAssetsOfUser(@PathVariable Long userId) {
        return stockService.getAllAssetsOfUser(userId);
    }

    // delete
    @DeleteMapping("/{assetId}")
    public void deleteAssetById(@PathVariable Long assetId) {
        stockService.deleteAssetById(assetId);
    }

}
