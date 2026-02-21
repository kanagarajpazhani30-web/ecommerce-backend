package com.e_commerce.order_api_service.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResilienceConfig {

    @Bean
    public CircuitBreaker inventoryCircuitBreaker(
            CircuitBreakerRegistry registry) {

        return registry.circuitBreaker("inventoryService");
    }
}
