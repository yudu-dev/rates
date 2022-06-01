package com.exchange.rates.controller.dto;


import lombok.Data;
import java.util.Map;

@Data
public class RatesDTO {

    private Map<String, Double> rates;

}
