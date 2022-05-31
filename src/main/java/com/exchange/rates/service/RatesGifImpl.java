package com.exchange.rates.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class RatesGifImpl implements RatesGifService {

    @Override
    public String yesterdayDateLink() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime yesterday = now.minusDays(1);
        String formattedYesterdayDate = yesterday.format(dtf);

        return "/historical/" + formattedYesterdayDate + ".json";

    }

    @Override
    public boolean compareTwoExchangeRates(double latestRate, double yesterdayRate) {
        return latestRate > yesterdayRate;
    }



}
