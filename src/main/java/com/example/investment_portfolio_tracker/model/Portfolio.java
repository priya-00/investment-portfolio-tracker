package com.example.investment_portfolio_tracker.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Portfolio {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;

    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "portfolio_user_id")
    @JsonBackReference
    private PortfolioUser portfolioUser;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL)
    @JsonManagedReference("user-portfolio")
    private List<Stock> stockList = new ArrayList<>();

}
