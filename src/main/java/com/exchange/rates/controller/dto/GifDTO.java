package com.exchange.rates.controller.dto;


import lombok.Data;

import java.util.Map;

@Data
public class GifDTO {

    private DataPayload data;

    @Data
    public static class DataPayload {
        private String id;
        private String url;
        private Map<String, ImageData> images;
    }

    @Data
    public static class ImageData {
        private String url;
    }
}
