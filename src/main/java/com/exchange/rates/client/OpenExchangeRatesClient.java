package com.exchange.rates.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@FeignClient(name = "ratesData", url = "${rates.url}")
public interface OpenExchangeRatesClient {

    @GetMapping(value = "/latest.json")
    ResponseEntity<?> getLatestRates(@RequestParam("app_id") String app_id,
                                     @RequestParam("base") String base,
                                     @RequestParam("symbols") String symbols);
    @GetMapping(value = "/historical/{yesterdayDateLink}")
    ResponseEntity<?> getYesterdayRates(@PathVariable("yesterdayDateLink") String getYesterdayDateLink,
                                        @RequestParam("app_id") String app_id,
                                        @RequestParam("base") String base,
                                        @RequestParam("symbols") String symbols);
}
