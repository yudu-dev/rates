package com.exchange.rates.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RatesYesterdayServiceImpl implements RatesYesterdayService {

    @Override
    public ResponseEntity<Map> getYesterdayRates() {
        return null;
    }

}

