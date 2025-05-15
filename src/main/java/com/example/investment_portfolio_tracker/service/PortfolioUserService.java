package com.example.investment_portfolio_tracker.service;

import com.example.investment_portfolio_tracker.model.PortfolioUser;
import com.example.investment_portfolio_tracker.repository.PortfolioUserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class PortfolioUserService {

    PortfolioUserRepository portfolioUserRepository;

    // create
    public PortfolioUser createNewUser(PortfolioUser portfolioUser) {
        log.info("Creating new user with name: {}", portfolioUser.getName() );
        return portfolioUserRepository.save(portfolioUser);
    }

    // update
    public PortfolioUser updateUserById(Long userId, PortfolioUser portfolioUser) {
        if(portfolioUser.getId() == null) {
            portfolioUser.setId(userId);
        }
        log.info("Updating user with ID: {}", userId);
        return portfolioUserRepository.save(portfolioUser);
    }

    // get
    public PortfolioUser getUserById(Long userId) {
        log.info("Fetching user with ID: {}", userId);
        return portfolioUserRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
    }

    // get all
    public List<PortfolioUser> getAllUsers() {
        log.info("Fetching all users in DB");
        return portfolioUserRepository.findAll();
    }

    // delete
    public void deleteUserById(@PathVariable Long userId) {
        log.info("Deleting user with ID: {}", userId);
        portfolioUserRepository.deleteById(userId);
    }

    // delete all
    public void deleteAllUsers() {
        log.info("Deleting all users in DB");
        portfolioUserRepository.deleteAll();
    }
}
