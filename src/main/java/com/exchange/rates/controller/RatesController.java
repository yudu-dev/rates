package com.exchange.rates.controller;

import com.exchange.rates.service.BrokeGifService;
import com.exchange.rates.service.RatesYesterdayService;
import com.exchange.rates.service.RichGifService;
import com.exchange.rates.service.RatesLatestService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class RatesController {
    private final RatesLatestService ratesLatestService;
    private final RatesYesterdayService ratesYesterdayService;
    private final RichGifService richGifService;
    private final BrokeGifService brokeGifService;

    @Autowired
    public RatesController(RatesLatestService ratesLatestService, RatesYesterdayService ratesYesterdayService,
                           RichGifService richGifService, BrokeGifService brokeGifService) {
        this.ratesLatestService = ratesLatestService;
        this.ratesYesterdayService = ratesYesterdayService;
        this.richGifService = richGifService;
        this.brokeGifService = brokeGifService;
    }

    @GetMapping(value = "/rates", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> getLatestRates() {
        try {
            String jsonString = String.valueOf(ratesLatestService.getLatestRates().getBody());
            JSONObject obj = new JSONObject(jsonString);
            String rates = obj.getJSONObject("rates").getString("RUB");
            System.out.println(rates);
            return ResponseEntity.ok(richGifService.getRichGif().getBody());
        } catch (Exception e) {
            return (ResponseEntity<?>) ResponseEntity.notFound();
        }
    }

}
