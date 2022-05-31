package com.exchange.rates.controller;

import com.exchange.rates.client.GifClient;
import com.exchange.rates.client.OpenExchangeRatesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class RatesGifController {
    private final OpenExchangeRatesClient openExchangeRatesClient;
    private final GifClient gifClient;

    @Autowired
    public RatesGifController(OpenExchangeRatesClient openExchangeRatesClient, GifClient gifClient) {
        this.openExchangeRatesClient = openExchangeRatesClient;
        this.gifClient = gifClient;
    }

    @Value("${api_key}")
    private String apiKey;
    @Value("${rich_tag}")
    private String richTag;
    @Value("${broke_tag}")
    private String brokeTag;
    @GetMapping(value = "/rates")
    ResponseEntity<?> getLatestRates() {
        return ResponseEntity.ok(gifClient.getRichGif(apiKey, richTag));

    }
}
