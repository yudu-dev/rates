package com.exchange.rates.service;

import com.exchange.rates.client.GifClient;
import com.exchange.rates.client.OpenExchangeRatesClient;
import com.exchange.rates.configuration.ApplicationProperties;
import lombok.RequiredArgsConstructor;
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
    private final ApplicationProperties properties;
    private final GifDownloadService gifDownloadService;
    /**
     * Возвращает ссылку на правильный GIF на основе метода compareTwoExchangeRates()
     *
     * @return GIF с тэгом Rich, если compareTwoExchangeRates() вернул TRUE, иначе - GIF с тэгом Broke
     */
    public byte[] getRightGif(String symbol) {
        String tag = compareTwoExchangeRates(symbol)
                ? properties.getIncreaseTag()
                : properties.getDecreaseTag();

        var gifDTO = gifClient.getGifByTag(properties.getGifApiKey(), tag).getBody();
        return gifDownloadService.getGifByDTO(gifDTO);
    }

    /**
     * Возвращает текстовую часть ссылки на вчерашний курс валют
     *
     * @return часть ссылки в текстовом формате
     */
    private String getYesterdayDateLink() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime yesterday = now.minusDays(1);
        String formattedYesterdayDate = yesterday.format(dtf);

        return formattedYesterdayDate + ".json";
    }

    /**
     * Сравнивает сегодняшний и вчерашний курсы валют
     *
     * @return true если курс по отношению к USD за сегодня стал выше вчерашнего, иначе false
     */
    public boolean compareTwoExchangeRates(String userSymbol) {
        String symbol = userSymbol == null
                ? properties.getDefaultSymbol()
                : userSymbol;
        Map<String, Double> latestRateMap = Objects.requireNonNull(openExchangeRatesClient
                .getLatestRates(properties.getAppId(), properties.getBase(), symbol)
                .getBody()).getRates();
        Double latestRate = latestRateMap.get(symbol);

        Map<String, Double> yesterdayRateMap = Objects.requireNonNull(openExchangeRatesClient
                .getYesterdayRates(getYesterdayDateLink(), properties.getAppId(), properties.getBase(), symbol)
                .getBody()).getRates();
        Double yesterdayRate = yesterdayRateMap.get(symbol);

        if(latestRate == null || yesterdayRate == null) {
            throw new IllegalArgumentException("Currency values not found");
        }
        return latestRate > yesterdayRate;
    }

}
