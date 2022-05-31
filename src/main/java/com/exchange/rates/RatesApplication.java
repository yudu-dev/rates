package com.exchange.rates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.exchange.rates.client",
		"com.exchange.rates.controller"})
public class RatesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatesApplication.class, args);
	}

}
