package com.e_commerce.order_api_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderApiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderApiServiceApplication.class, args);
	}

}
