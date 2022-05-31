package com.exchange.rates.client;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class YesterdayLink {

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd");
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime yesterday = now.minusDays(1);
    String formattedYesterdayDate = yesterday.format(dtf);

    public static final String YESTERDAY_URL = "/historical" + formattedYesterdayDate + ".json";
}
