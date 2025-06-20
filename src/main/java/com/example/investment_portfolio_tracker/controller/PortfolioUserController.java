package com.example.investment_portfolio_tracker.controller;

import com.example.investment_portfolio_tracker.dto.PortfolioUserDto;
import com.example.investment_portfolio_tracker.model.PortfolioUser;
import com.example.investment_portfolio_tracker.service.PortfolioUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/portfolioUser")
@AllArgsConstructor
public class PortfolioUserController {

    PortfolioUserService portfolioUserService;

    // create
    @PostMapping
    public PortfolioUser createNewUser(@RequestBody PortfolioUserDto portfolioUserDto) {
        return portfolioUserService.createNewUser(portfolioUserDto);
    }

    // update
    @PutMapping("/{userId}")
    public ResponseEntity<PortfolioUserDto> updateUserById(@PathVariable Long userId, @RequestBody PortfolioUserDto portfolioUserDto) {
        return portfolioUserService.updateUserById(userId, portfolioUserDto);
    }

    // get
    @GetMapping("/{userId}")
    public PortfolioUser getUserById(@PathVariable Long userId) {
        return portfolioUserService.getUserById(userId);
    }

    // get all
    @GetMapping
    public List<PortfolioUser> getAllUsers() {
        return portfolioUserService.getAllUsers();
    }

    // delete
    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable Long userId) {
        portfolioUserService.deleteUserById(userId);
    }

    // delete all
    @DeleteMapping
    public void deleteAllUsers() {
        portfolioUserService.deleteAllUsers();
    }
}
