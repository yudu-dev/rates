package com.exchange.rates.client;

import com.exchange.rates.controller.dto.GifDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "GifData", url = "${client.gif.url}")
public interface GifClient {
    @GetMapping(value = "/random")
    ResponseEntity<GifDTO> getGifByTag(@RequestParam("api_key") String apiKey, @RequestParam("tag") String tag);

}
