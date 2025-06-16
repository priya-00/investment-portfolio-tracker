package com.example.investment_portfolio_tracker.service;

import com.example.investment_portfolio_tracker.dto.PortfolioDto;
import com.example.investment_portfolio_tracker.exception.PortfolioNotFoundException;
import com.example.investment_portfolio_tracker.exception.PortfolioUserNotFoundException;
import com.example.investment_portfolio_tracker.model.Portfolio;
import com.example.investment_portfolio_tracker.model.PortfolioUser;
import com.example.investment_portfolio_tracker.repository.PortfolioRepository;
import com.example.investment_portfolio_tracker.repository.PortfolioUserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;
    @Autowired
    private PortfolioUserRepository portfolioUserRepository;

    // create
    public Portfolio createNewPortfolio(Portfolio portfolio, Long userId) {
        log.info("Creating new portfolio with name: {}", portfolio.getName() );
        PortfolioUser portfolioUser = portfolioUserRepository.findById(userId).orElseThrow(() -> new PortfolioUserNotFoundException("Portfolio user not found."));
        portfolio.setPortfolioUser(portfolioUser);
        return portfolioRepository.save(portfolio);
    }

    // update
    public ResponseEntity<PortfolioDto> updatePortfolioById(Long portfolioId, PortfolioDto portfolioDto) {

        Portfolio portfolio = portfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new PortfolioNotFoundException("Portfolio not found. Please check the portfolio ID."));

        PortfolioUser portfolioUser = portfolio.getPortfolioUser();
        portfolio.setName(portfolioDto.getName());
        portfolio.setPortfolioUser(portfolioUser);

        portfolioRepository.save(portfolio);

        log.info("Updating portfolio with ID: {}", portfolioId);

        return ResponseEntity.ok(portfolioDto);
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
