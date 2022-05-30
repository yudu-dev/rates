package com.exchange.rates.controller;

import com.exchange.rates.client.IGifClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GifController {
    private final IGifClient gifClient;

    @Autowired
    public GifController(IGifClient gifClient) {
        this.gifClient = gifClient;
    }

    @GetMapping(path = "/gif")
    ResponseEntity<Map> getGif() {
        return ResponseEntity.ok(gifClient.getGif().getBody());
    }
}
