package com.example.investment_portfolio_tracker.controller;

import com.example.investment_portfolio_tracker.dto.StockDto;
import com.example.investment_portfolio_tracker.model.Stock;
import com.example.investment_portfolio_tracker.service.StockService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stocks")
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

    // get all by portfolio ID
    @GetMapping("/{portfolioId}")
    public List<Stock> getStockByPortfolioId(@PathVariable Long portfolioId) {
        return stockService.getStockByPortfolioId(portfolioId);
    }

    // delete
    @DeleteMapping("/{stockId}")
    public void deleteStockById(@PathVariable Long stockId) {
        stockService.deleteStockById(stockId);
    }

}
