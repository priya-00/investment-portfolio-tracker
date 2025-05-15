package com.example.investment_portfolio_tracker.repository;

import com.example.investment_portfolio_tracker.model.PortfolioUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioUserRepository extends JpaRepository<PortfolioUser, Long> {
}
