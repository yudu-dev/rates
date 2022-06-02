package com.exchange.rates.service;

import com.exchange.rates.client.GifClient;
import com.exchange.rates.client.OpenExchangeRatesClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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


    @Value("${app_id}")
    private String appId;
    @Value("${base}")
    private String base;
    @Value("${symbols}")
    private String symbols;
    /**
     * Сравнивает сегодняшний и вчерашний курсы валют
     * @return true если курс по отношению к USD за сегодня стал выше вчерашнего, иначе false
     */
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

    /**
     * Возвращает ссылку на правильный GIF на основе метода compareTwoExchangeRates()
     * @return GIF с тэгом Rich, если compareTwoExchangeRates() вернул TRUE, иначе - GIF с тэгом Broke
     */
    public String getRightGif() {
        String tag = compareTwoExchangeRates()
                ? richTag
                : brokeTag;
        return Objects.requireNonNull(gifClient.getGifByTag(apiKey, tag).getBody()).getData().getUrl();
    }

}
