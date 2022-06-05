package com.exchange.rates.service;

import com.exchange.rates.controller.dto.GifDTO;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Service
public class StraightGifDownloadService implements GifDownloadService {

    private static final String GIPHY_STORAGE_URL_PATTERN = "https://i.giphy.com/media/%s/giphy.gif";

    @Override
    public byte[] getGifByDTO(GifDTO gifDTO) {
        String id = gifDTO.getData().getId();
        String url = GIPHY_STORAGE_URL_PATTERN.formatted(id);

        byte[] result;
        try (InputStream input = new URL(url).openStream()) {
            result = IOUtils.toByteArray(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
