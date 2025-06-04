package com.example.investment_portfolio_tracker.controller;

import com.example.investment_portfolio_tracker.dto.StockDto;
import com.example.investment_portfolio_tracker.model.Stock;
import com.example.investment_portfolio_tracker.service.StockService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock")
@AllArgsConstructor
public class StockController {

    StockService stockService;

    // create
    @PostMapping("/{portfolioId}")
    public Stock createNewAsset(@PathVariable Long portfolioId, @RequestBody StockDto stockDto) {
        return stockService.createNewAsset(portfolioId, stockDto);
    }

    // update
    @PutMapping("/{stockId}")
    public ResponseEntity<StockDto> updateStockById(@PathVariable Long stockId, @RequestBody StockDto stockDto) {
        return stockService.updateStockById(stockId, stockDto);
    }

    // get
    @GetMapping("/{stockId}")
    public Stock getAssetById(@PathVariable Long stockId) {
        return stockService.getAssetById(stockId);
    }

    // delete
    @DeleteMapping("/{stockId}")
    public void deleteAssetById(@PathVariable Long stockId) {
        stockService.deleteAssetById(stockId);
    }

}
