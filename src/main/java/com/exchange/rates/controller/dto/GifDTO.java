package com.exchange.rates.controller.dto;


import lombok.Data;

@Data
public class GifDTO {

    private DataPayLoad data;

    @Data
    public static class DataPayLoad {
        private String url;
    }

}
