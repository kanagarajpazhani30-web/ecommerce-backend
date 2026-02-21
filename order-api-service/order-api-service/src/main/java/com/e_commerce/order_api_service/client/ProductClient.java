package com.e_commerce.order_api_service.client;

import com.e_commerce.order_api_service.entity.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ProductClient {

    private final WebClient webClient;

    public ProductClient(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("http://localhost:8090/api/product/")
                .build();
    }

    public Mono<Product> getProduct(Long productId) {

        return webClient.get()
                .uri("{id}", productId)
                .retrieve()
                .bodyToMono(Product.class);
    }
}
