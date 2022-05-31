package com.exchange.rates.service;

import java.util.List;

public class RatesGifService {

    /**
     * Возвращает текстовую часть ссылки на вчерашний курс валют
     * @return часть ссылки в текстовом формате
     */
    String yesterdayDateLink();

    /**
     * Сравнивает сегодняшний и вчерашний курсы валют
     * @param latestRate сегодняшний курс валют
     * @param yesterdayRate вчерашний курс валют
     * @return true если курс по отношению к USD за сегодня стал выше вчерашнего, иначе false
     */
    boolean compareTwoExchangeRates(double latestRate, double yesterdayRate);
}
