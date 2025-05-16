package com.example.investment_portfolio_tracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockResponse {

    private String ticker;
    private String name;
    private double price;
    private String exchange;
    private String currency;
}
