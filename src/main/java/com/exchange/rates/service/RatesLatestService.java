package com.exchange.rates.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(name = "ratesLatestData", url = "${latest.rates.url}")
public interface RatesLatestService {
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<Map> getLatestRates();
}
