package com.exchange.rates.service;

import com.exchange.rates.client.GifClient;
import com.exchange.rates.client.OpenExchangeRatesClient;
import com.exchange.rates.controller.dto.GifDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RatesGifService {

    private final OpenExchangeRatesClient openExchangeRatesClient;
    private final GifClient gifClient;

    /**
     * Возвращает текстовую часть ссылки на вчерашний курс валют
     * @return часть ссылки в текстовом формате
     */
    public String getYesterdayDateLink() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime yesterday = now.minusDays(1);
        String formattedYesterdayDate = yesterday.format(dtf);

        return formattedYesterdayDate + ".json";
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
    public boolean compareTwoExchangeRates() {
        Map<String, Double> latestRateMap = Objects.requireNonNull(openExchangeRatesClient
                                            .getLatestRates(appId, base, symbols)
                                            .getBody()).getRates();
        Double latestRate = latestRateMap.get(symbols);
        Map<String, Double> yesterdayRateMap = Objects.requireNonNull(openExchangeRatesClient
                                            .getYesterdayRates(getYesterdayDateLink(), appId, base, symbols)
                                            .getBody()).getRates();
        Double yesterdayRate = yesterdayRateMap.get(symbols);
        return latestRate > yesterdayRate;
    }

    @Value("${api_key}")
    private String apiKey;
    @Value("${rich_tag}")
    private String richTag;
    @Value("${broke_tag}")
    private String brokeTag;
    public ResponseEntity<GifDTO> getRightGif(boolean compareTwoExchangeRates) {
        if (compareTwoExchangeRates) {
            return gifClient.getRichGif(apiKey, richTag);
        } else {
            return gifClient.getBrokeGif(apiKey, brokeTag);
        }
    }

}
