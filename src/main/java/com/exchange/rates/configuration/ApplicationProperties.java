package com.exchange.rates.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties
public class ApplicationProperties {
    @Value("${client.rates.app_id}")
    private String appId;
    @Value("${client.gif.api_key}")
    private String gifApiKey;

    @Value("${currency.base}")
    private String base;
    @Value("${currency.default}")
    private String defaultSymbol;

    @Value("${increase_tag}")
    private String increaseTag;
    @Value("${decrease_tag}")
    private String decreaseTag;


}
