package com.exchange.rates.client;

import com.exchange.rates.configuration.ApplicationProperties;
import com.exchange.rates.controller.dto.GifDTO;
import com.exchange.rates.controller.dto.RatesDTO;
import com.exchange.rates.service.GifDownloadService;
import com.exchange.rates.service.RatesGifService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@RunWith(SpringRunner.class)
@Import({RatesGifService.class, ApplicationProperties.class})
@TestPropertySource(
        properties = {
                "increase_tag=rich",
                "decrease_tag=broke"
        }
)
public class OpenExchangeRatesClientTest {

    @MockBean
    OpenExchangeRatesClient mockOpenExchangeRatesClient;

    @MockBean
    GifClient mokGifClient;

    @MockBean
    GifDownloadService gifDownloadService;


    @Captor
    ArgumentCaptor<GifDTO> gifDTOCaptor;


    @Autowired
    RatesGifService sut;


    @Test
    public void shouldReturnRichGifUrlWhenLatestRateGreaterThenYesterdayRate() {
        Mockito.when(mockOpenExchangeRatesClient.getYesterdayRates(any(), any(), any(), any()))
                .thenReturn(buildRatesDTO("RUB", 55.2));
        Mockito.when(mockOpenExchangeRatesClient.getLatestRates(any(), any(), any()))
                .thenReturn(buildRatesDTO("RUB", 66.6));

        Mockito.when(mokGifClient.getGifByTag(any(), eq("rich"))).thenReturn(buildGifDTO("richGifId"));
        Mockito.when(mokGifClient.getGifByTag(any(), eq("broke"))).thenReturn(buildGifDTO("brokeGifId"));

        sut.getRightGif("RUB");

        Mockito.verify(gifDownloadService).getGifByDTO(gifDTOCaptor.capture());
        GifDTO result = gifDTOCaptor.getValue();
        assertEquals(result.getData().getId(), "richGifId");
    }

    @Test
    public void shouldReturnBrokeGifUrlWhenLatestRateLessThenYesterdayRate() {
        Mockito.when(mockOpenExchangeRatesClient.getYesterdayRates(any(), any(), any(), any()))
                .thenReturn(buildRatesDTO("RUB", 77.7));
        Mockito.when(mockOpenExchangeRatesClient.getLatestRates(any(), any(), any()))
                .thenReturn(buildRatesDTO("RUB", 66.6));

        Mockito.when(mokGifClient.getGifByTag(any(), eq("rich"))).thenReturn(buildGifDTO("richGifId"));
        Mockito.when(mokGifClient.getGifByTag(any(), eq("broke"))).thenReturn(buildGifDTO("brokeGifId"));

        sut.getRightGif("RUB");

        Mockito.verify(gifDownloadService).getGifByDTO(gifDTOCaptor.capture());
        GifDTO result = gifDTOCaptor.getValue();
        assertEquals(result.getData().getId(), "brokeGifId");
    }

    private ResponseEntity<GifDTO> buildGifDTO(String gifId) {
        GifDTO.DataPayload gifDataPayload = new GifDTO.DataPayload();
        gifDataPayload.setId(gifId);
        GifDTO gifDTO = new GifDTO();
        gifDTO.setData(gifDataPayload);
        return ResponseEntity.ok(gifDTO);
    }

    private ResponseEntity<RatesDTO> buildRatesDTO(String currency, Double amount) {
        HashMap<String, Double> ratesMap = new HashMap<>();
        ratesMap.put(currency, amount);
        RatesDTO rateDTO = new RatesDTO();
        rateDTO.setRates(ratesMap);
        return ResponseEntity.ok(rateDTO);
    }
}