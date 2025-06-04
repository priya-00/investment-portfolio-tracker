package com.example.investment_portfolio_tracker.controller;

import com.example.investment_portfolio_tracker.dto.PortfolioDto;
import com.example.investment_portfolio_tracker.model.Portfolio;
import com.example.investment_portfolio_tracker.model.Stock;
import com.example.investment_portfolio_tracker.service.PortfolioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sound.sampled.Port;
import java.util.List;

@RestController
@RequestMapping("/portfolio")
@AllArgsConstructor
public class PortfolioController {

    PortfolioService portfolioService;

    // create
    @PostMapping("/{userId}")
    public Portfolio createNewPortfolio(@PathVariable Long userId, @RequestBody Portfolio portfolio) {
        return portfolioService.createNewPortfolio(portfolio, userId);
    }

    // update
    @PutMapping("/{portfolioId}")
    public ResponseEntity<PortfolioDto> updatePortfolioById(@PathVariable Long portfolioId, @RequestBody PortfolioDto portfolioDto) {
        return portfolioService.updatePortfolioById(portfolioId, portfolioDto);
    }

    // get
    @GetMapping("/{portfolioId}")
    public Portfolio getPortfolioById(@PathVariable Long portfolioId) {
        return portfolioService.getPortfolioById(portfolioId);
    }

    // get all
    @GetMapping("/user/{userId}")
    public List<Portfolio> getAllPortfoliosOfUser(@PathVariable Long userId) {
        return portfolioService.getAllPortfoliosOfUser(userId);
    }

    // delete
    @DeleteMapping("/{portfolioId}")
    public void deletePortfolioById(@PathVariable Long portfolioId) {
        portfolioService.deletePortfolioById(portfolioId);
    }
}
