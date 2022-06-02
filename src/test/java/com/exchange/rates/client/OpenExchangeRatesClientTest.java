/**

package com.exchange.rates.client;

import com.exchange.rates.controller.dto.GifDTO;
import com.exchange.rates.controller.dto.RatesDTO;
import com.exchange.rates.service.RatesGifService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
public class OpenExchangeRatesClientTest {

    @MockBean
    OpenExchangeRatesClient mockOpenExchangeRatesClient;

    @MockBean
    GifClient mokGifClient;

    @Autowired
    ApplicationContext context;

    @Test
    public void shouldReturnRichGifUrlWhenLatestRateBiggerThenYesterdayRate() {
        HashMap<String, Double> latestRateMap = new HashMap<>();
        latestRateMap.put("RUB", 65.2);
        RatesDTO latestRateDTO = new RatesDTO();

        HashMap<String, Double> yesterdayRateMap = new HashMap<>();
        yesterdayRateMap.put("RUB", 55.2);
        RatesDTO yesterdayRateDTO = new RatesDTO();

        latestRateDTO.setRates(latestRateMap);
        ResponseEntity<RatesDTO> latestRate = ResponseEntity.ok(latestRateDTO);

        yesterdayRateDTO.setRates(yesterdayRateMap);
        ResponseEntity<RatesDTO> yesterdayRate = ResponseEntity.ok(yesterdayRateDTO);

        Mockito.when(mockOpenExchangeRatesClient.getLatestRates(any(), any(), any())).thenReturn(latestRate);
        Mockito.when(mockOpenExchangeRatesClient.getYesterdayRates(any(), any(), any(), any())).thenReturn(yesterdayRate);

        GifDTO.DataPayload gifDataPayload = new GifDTO.DataPayload();
        String gifUrl = "https://www.site.com/gif";
        gifDataPayload.setUrl(gifUrl);
        GifDTO gifUrlDTO = new GifDTO();
        gifUrlDTO.setData(gifDataPayload);
        ResponseEntity<GifDTO> stringGifUrl = ResponseEntity.ok(gifUrlDTO);

        Mockito.when(mokGifClient.getGifByTag(any(), any())).thenReturn(stringGifUrl);

        RatesGifService sut = context.getBean(RatesGifService.class);

        String stringRightGif = sut.getRightGif();

        System.out.println("Я тут");
        Assert.assertEquals("https://www.site.com/gif", stringRightGif);

    }

}

 */