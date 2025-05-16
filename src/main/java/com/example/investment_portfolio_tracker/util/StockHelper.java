package com.example.investment_portfolio_tracker.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StockHelper {

    public String getStockTicker(String stockName) {
        Map<String, String> stockTickerMapper = new HashMap<>();
        stockTickerMapper.put("Microsoft", "MSFT");
        stockTickerMapper.put("Nvidia", "NVDA");
        stockTickerMapper.put("Apple", "AAPL");
        stockTickerMapper.put("Amazon", "AMZN");
        stockTickerMapper.put("Google", "GOOGL");
        stockTickerMapper.put("Meta", "META");
        stockTickerMapper.put("Tesla", "TSLA");
        stockTickerMapper.put("JP Morgan", "JPM");
        stockTickerMapper.put("Visa", "V");
        stockTickerMapper.put("Mastercard", "MA");
        stockTickerMapper.put("Netflix", "NFLX");
        stockTickerMapper.put("Oracle", "ORCL");

        return stockTickerMapper.get(stockName);
    }
}
