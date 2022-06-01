package com.exchange.rates.service;

import com.exchange.rates.client.GifClient;
import com.exchange.rates.client.OpenExchangeRatesClient;
import com.exchange.rates.controller.dto.GifDTO;
import com.exchange.rates.controller.dto.RatesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RatesGifService {

    private final OpenExchangeRatesClient openExchangeRatesClient;
    private final GifClient gifClient;
    @Autowired
    public RatesGifService(OpenExchangeRatesClient openExchangeRatesClient, GifClient gifClient) {
        this.openExchangeRatesClient = openExchangeRatesClient;
        this.gifClient = gifClient;
    }

    /**
     * Возвращает текстовую часть ссылки на вчерашний курс валют
     * @return часть ссылки в текстовом формате
     */
    String getYesterdayDateLink() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime yesterday = now.minusDays(1);
        String formattedYesterdayDate = yesterday.format(dtf);

        return "/historical/" + formattedYesterdayDate + ".json";
    }

    /**
     * Сравнивает сегодняшний и вчерашний курсы валют
     * @param latestRate сегодняшний курс валют
     * @param yesterdayRate вчерашний курс валют
     * @return true если курс по отношению к USD за сегодня стал выше вчерашнего, иначе false
     */
    @Value("${app_id}")
    private String appId;
    @Value("${base}")
    private String base;
    @Value("${symbols}")
    private String symbols;
    boolean compareTwoExchangeRates(ResponseEntity<RatesDTO> latestRate, ResponseEntity<RatesDTO> yesterdayRate) {
        latestRate = openExchangeRatesClient.getLatestRates(appId, base, symbols);
        yesterdayRate = openExchangeRatesClient.getYesterdayRates(getYesterdayDateLink(), appId, base, symbols);
        return true;
    }

    @Value("${api_key}")
    private String apiKey;
    @Value("${rich_tag}")
    private String richTag;
    @Value("${broke_tag}")
    private String brokeTag;
    ResponseEntity<GifDTO> getRightGif(boolean compareTwoExchangeRates) {
        if (compareTwoExchangeRates) {
            return gifClient.getRichGif(apiKey, richTag);
        } else {
            return gifClient.getBrokeGif(apiKey, brokeTag);
        }
    }

}
