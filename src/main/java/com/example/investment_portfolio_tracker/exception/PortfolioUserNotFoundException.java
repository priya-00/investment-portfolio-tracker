package com.example.investment_portfolio_tracker.exception;

public class PortfolioUserNotFoundException extends RuntimeException {
    public PortfolioUserNotFoundException(String message) {
        super(message);
    }
}
