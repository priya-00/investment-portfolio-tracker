package com.example.investment_portfolio_tracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockDto {

    @NotBlank
    private String name;

    @NotNull(message = "Quantity of shares to be purchased is required.")
    private int quantity;
}
