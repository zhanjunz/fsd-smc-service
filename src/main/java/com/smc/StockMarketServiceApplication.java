package com.smc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@RestController
@SpringBootApplication
public class StockMarketServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(StockMarketServiceApplication.class, args);
	}
	
}
