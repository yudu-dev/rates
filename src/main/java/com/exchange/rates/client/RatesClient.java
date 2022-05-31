package com.exchange.rates.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@FeignClient(name = "ratesData", url = "${rates.url}")
public interface RatesClient {


    @GetMapping(value = "/latest.json")
    ResponseEntity<?> getLatestRates(@RequestParam("app_id") String app_id,
                                     @RequestParam("base") String base,
                                     @RequestParam("symbols") String symbols);
    @GetMapping(value = {YesterdayLink.YESTERDAY_URL})
    ResponseEntity<?> getYesterdayRates(@RequestParam("app_id") String app_id,
                                        @RequestParam("base") String base,
                                        @RequestParam("symbols") String symbols);
}
