package com.e_commerce.cart_api_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

    @Bean("productWebClient")
    public WebClient productClient(){
        return WebClient.builder().baseUrl("http://localhost:8090/api/product/").build();
    }
}
