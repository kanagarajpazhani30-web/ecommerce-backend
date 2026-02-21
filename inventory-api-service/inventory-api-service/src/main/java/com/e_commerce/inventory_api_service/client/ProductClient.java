package com.e_commerce.inventory_api_service.client;

import com.e_commerce.inventory_api_service.dto.ProductDto;
import com.e_commerce.inventory_api_service.response.ProductResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductClient {

    private final WebClient webClient;

    public ProductClient(WebClient.Builder webClient) {
        this.webClient = webClient.baseUrl("http://localhost:8090/api/product").build();
    }

    public Flux<ProductResponse> getAllProducts(){
        return webClient.get().retrieve().bodyToFlux(ProductResponse.class);
    }
}
