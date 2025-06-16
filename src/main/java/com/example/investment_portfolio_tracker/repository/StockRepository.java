package com.example.investment_portfolio_tracker.repository;

import com.example.investment_portfolio_tracker.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findAllByPortfolioId(Long portfolioId);
}
