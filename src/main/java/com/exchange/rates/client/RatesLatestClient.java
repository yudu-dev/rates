package com.exchange.rates.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ratesLatestData", url = "${latest.rates.url}")
public interface RatesLatestClient {
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<?> getLatestRates(@RequestParam("app_id") String app_id,
                                     @RequestParam("base") String base,
                                     @RequestParam("symbols") String symbols);
}
