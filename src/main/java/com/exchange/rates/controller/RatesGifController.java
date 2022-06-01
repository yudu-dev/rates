package com.exchange.rates.controller;

import com.exchange.rates.controller.dto.GifDTO;
import com.exchange.rates.service.RatesGifService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequiredArgsConstructor
public class RatesGifController {
    private final RatesGifService ratesGifService;

    @GetMapping(value = "/rates")
    public ResponseEntity<GifDTO> getRightGif() {
        String rigthGifUrl = ratesGifService.getRightGif
        return здесьХзЧто
                ? new ResponseEntity<>(ResponseEntity<GifDTO>, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }


}
