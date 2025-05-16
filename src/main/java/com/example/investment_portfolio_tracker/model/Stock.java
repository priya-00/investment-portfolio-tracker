package com.example.investment_portfolio_tracker.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    private String ticker;

    @NotNull
    private int quantity;

    @NotNull
    private double value;

    @NotBlank
    private String exchange;

    @NotBlank
    private String currency;

    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    @JsonBackReference("user-portfolio")
    private Portfolio portfolio;
}
