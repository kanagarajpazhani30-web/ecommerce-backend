package com.e_commerce.product_api_service;

import com.e_commerce.product_api_service.entities.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductApiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApiServiceApplication.class, args);
	}

}
