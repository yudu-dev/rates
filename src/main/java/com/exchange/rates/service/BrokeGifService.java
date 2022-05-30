package com.exchange.rates.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(name = "brokeGifData", url = "${broke.gif.client.url}")
public interface BrokeGifService {
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<Map> getBrokeGif();

}
