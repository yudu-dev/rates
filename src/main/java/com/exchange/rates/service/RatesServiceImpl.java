package com.exchange.rates.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RatesServiceImpl implements RatesService {

    @Override
    public ResponseEntity<Map> getRates() {
        return null;
    }

}

