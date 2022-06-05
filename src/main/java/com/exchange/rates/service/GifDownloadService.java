package com.exchange.rates.service;

import com.exchange.rates.controller.dto.GifDTO;

public interface GifDownloadService {
    byte[] getGifByDTO(GifDTO gifDTO);
}
