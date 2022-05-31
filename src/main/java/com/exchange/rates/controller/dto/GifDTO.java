package com.exchange.rates.controller.dto;


import lombok.Data;

@Data
public class GifDTO {

    private DataPayload data;

    @Data
    public static class DataPayload {
        private String url;
    }

}
