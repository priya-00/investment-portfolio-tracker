package com.example.investment_portfolio_tracker.service;

import com.example.investment_portfolio_tracker.model.Portfolio;
import com.example.investment_portfolio_tracker.model.PortfolioUser;
import com.example.investment_portfolio_tracker.model.Stock;
import com.example.investment_portfolio_tracker.repository.PortfolioRepository;
import com.example.investment_portfolio_tracker.repository.PortfolioUserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class PortfolioService {

    PortfolioRepository portfolioRepository;
    PortfolioUserRepository portfolioUserRepository;

    // create
    public Portfolio createNewPortfolio(Portfolio portfolio, Long userId) {
        log.info("Creating new portfolio with name: {}", portfolio.getName() );
        portfolio.setPortfolioUser(portfolioUserRepository.getById(userId));
        return portfolioRepository.save(portfolio);
    }

    // update
    public Portfolio updatePortfolioById(Long portfolioId, Portfolio portfolio) {
        if(portfolio.getId() == null) {
            portfolio.setId(portfolioId);
        }
        log.info("Updating portfolio with ID: {}", portfolioId);
        return portfolioRepository.save(portfolio);
    }

    // get
    public Portfolio getPortfolioById(Long portfolioId) {
        log.info("Fetching portfolio with ID: {}", portfolioId);
        return portfolioRepository.findById(portfolioId).orElseThrow(() -> new EntityNotFoundException("Portfolio not found with ID: " + portfolioId));
    }

    // get all
    public List<Portfolio> getAllPortfoliosOfUser(Long userId) {
        log.info("Fetching all portfolios in DB for user with ID: {}", userId);
        return portfolioRepository.findAllByPortfolioUserId(userId);
    }

    // delete
    public void deletePortfolioById(Long portfolioId) {
        log.info("Deleting portfolio with ID: {}", portfolioId);
        portfolioRepository.deleteById(portfolioId);
    }
}
