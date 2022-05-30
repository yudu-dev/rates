package com.exchange.rates.controller;

import com.exchange.rates.service.BrokeGifService;
import com.exchange.rates.service.RatesYesterdayService;
import com.exchange.rates.service.RichGifService;
import com.exchange.rates.service.RatesLatestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class RatesController {
    private final RatesLatestService ratesLatestService;
    private final RatesYesterdayService ratesYesterdayService;
    private final RichGifService richGifService;
    private final BrokeGifService brokeGifService;

    @Autowired
    public RatesController(RatesLatestService ratesService, RatesYesterdayService ratesYesterdayService,
                           RichGifService richGifService, BrokeGifService brokeGifService) {
        this.ratesLatestService = ratesService;
        this.ratesYesterdayService = ratesYesterdayService;
        this.richGifService = richGifService;
        this.brokeGifService = brokeGifService;
    }

    @GetMapping(path = "/rates")
    ResponseEntity<Map> getLatestRates() {
        return ResponseEntity.ok(richGifService.getRichGif().getBody());
    }

}
