package com.exchange.rates.controller;

import com.exchange.rates.service.RatesGifService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequiredArgsConstructor
public class RatesGifController {
    private final RatesGifService ratesGifService;

    @GetMapping(value = "/rates")
    public String getRightGif() {
        return ratesGifService.getRightGif(ratesGifService.compareTwoExchangeRates());
    }

}
