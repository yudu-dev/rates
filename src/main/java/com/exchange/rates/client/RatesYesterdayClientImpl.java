package com.exchange.rates.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RatesYesterdayClientImpl implements RatesYesterdayClient {

    @Override
    public ResponseEntity<Map> getYesterdayRates() {
        return null;
    }

}

