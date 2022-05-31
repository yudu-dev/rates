package com.exchange.rates.controller;

import com.exchange.rates.client.GifClient;
import com.exchange.rates.client.RatesLatestClient;
import com.exchange.rates.client.RatesYesterdayClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class RatesController {
    private final RatesLatestClient ratesLatestClient;
    private final RatesYesterdayClient ratesYesterdayClient;
    private final GifClient gifClient;

    @Autowired
    public RatesController(RatesLatestClient ratesLatestClient, RatesYesterdayClient ratesYesterdayClient,
                           GifClient gifClient) {
        this.ratesLatestClient = ratesLatestClient;
        this.ratesYesterdayClient = ratesYesterdayClient;
        this.gifClient = gifClient;
    }

    @Value("${api_key}")
    private String apiKey;
    @Value("${tag}")
    private String tag;
    @GetMapping(value = "/rates")
    ResponseEntity<?> getLatestRates() {
        return ResponseEntity.ok(gifClient.getGif(apiKey, tag));
    }
}
