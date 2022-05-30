package com.exchange.rates.controller;

import com.exchange.rates.service.RatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RatesController {
    private final RatesService ratesService;

    @Autowired
    public RatesController(RatesService ratesClient) {
        this.ratesService = ratesClient;
    }

    @GetMapping(path = "/rates")
    ResponseEntity<Map> getRates() {
        return ResponseEntity.ok(ratesService.getRates().getBody());
    }
}
