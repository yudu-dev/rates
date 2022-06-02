package com.exchange.rates.controller;

import com.exchange.rates.service.RatesGifService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RatesGifController {
    private final RatesGifService ratesGifService;

    @GetMapping(value = "/rates", produces = MediaType.IMAGE_GIF_VALUE)
    public ResponseEntity<byte[]> getRightGif(@RequestParam(required = false) String currency) {
        return new ResponseEntity<>(ratesGifService.getRightGif(currency), HttpStatus.OK);
    }
}
