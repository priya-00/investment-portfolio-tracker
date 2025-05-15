package com.example.investment_portfolio_tracker.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class PortfolioUser {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @OneToMany(mappedBy = "portfolioUser", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Portfolio> portfolioList = new ArrayList<>();
}
