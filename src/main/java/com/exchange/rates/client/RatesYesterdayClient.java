package com.exchange.rates.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(name = "ratesYesterdayData", url = "${yesterday.rates.url}")
public interface RatesYesterdayClient {
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<Map> getYesterdayRates();
}
