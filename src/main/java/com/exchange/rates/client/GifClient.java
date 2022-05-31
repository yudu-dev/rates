package com.exchange.rates.client;

import com.exchange.rates.controller.dto.GifDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "GifData", url = "${gif.client.url}")
public interface GifClient {
    @GetMapping(value = "/random")
    ResponseEntity<GifDTO> getRichGif(@RequestParam("api_key") String apiKey, @RequestParam("rich_tag") String tag);

    @GetMapping(value = "/random")
    ResponseEntity<GifDTO> getBrokeGif(@RequestParam("api_key") String apiKey, @RequestParam("broke_tag") String tag);

}
