package com.example.investment_portfolio_tracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Stock {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private double value;

    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    private PortfolioUser portfolioUser;

    @ManyToOne
    private Portfolio portfolio;
}
