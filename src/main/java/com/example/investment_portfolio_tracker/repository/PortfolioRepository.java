package com.example.investment_portfolio_tracker.repository;

import com.example.investment_portfolio_tracker.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    List<Portfolio> findAllByPortfolioUserId(Long userId);
}
