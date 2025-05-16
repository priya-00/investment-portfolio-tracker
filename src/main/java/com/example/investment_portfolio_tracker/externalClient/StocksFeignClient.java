package com.example.investment_portfolio_tracker.externalClient;

import com.example.investment_portfolio_tracker.model.StockResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "feignStocksClient", url = "https://api.api-ninjas.com")
public interface StocksFeignClient {

    @GetMapping("/v1/stockprice")
    StockResponse getStockPrice(@RequestParam("ticker") String ticker, @RequestHeader("X-Api-Key") String apiKey);
}
