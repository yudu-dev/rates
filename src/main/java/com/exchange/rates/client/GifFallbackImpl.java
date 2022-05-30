package com.exchange.rates.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GifFallbackImpl implements IGifClient {

    @Override
    public ResponseEntity<Map> getGif() {
        return null;
    }

}

