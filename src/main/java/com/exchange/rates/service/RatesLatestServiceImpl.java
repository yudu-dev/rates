package com.exchange.rates.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RatesLatestServiceImpl implements RatesLatestService {

    @Override
    public ResponseEntity<Map> getLatestRates() {
        return null;
    }

}

