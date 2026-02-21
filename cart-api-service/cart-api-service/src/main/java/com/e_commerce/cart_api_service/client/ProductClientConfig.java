package com.e_commerce.cart_api_service.client;

import com.e_commerce.cart_api_service.model.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ProductClientConfig {
    private  final WebClient productClient;

    public ProductClientConfig(WebClient productClient) {
        this.productClient = productClient;
    }


    public Mono<ProductResponse> getProductDetails(long id){
        return productClient.get().uri("/{id}", id).exchangeToMono(this::handleResponse).doOnNext(res -> System.out.println("Mapped Response: " + res));
    }

    private Mono<ProductResponse> handleResponse(ClientResponse response) {

        if (response.statusCode().is4xxClientError()) {
            return response.bodyToMono(String.class)
                    .flatMap(error -> {
                        System.out.println("4xx Error from Product API: " + error);
                        return Mono.error(new RuntimeException("Product not found"));
                    });
        }

        if (response.statusCode().is5xxServerError()) {
            return response.bodyToMono(String.class)
                    .flatMap(error -> {
                        System.out.println("5xx Error from Product API: " + error);
                        return Mono.error(new RuntimeException("Product API internal error"));
                    });
        }

        // SUCCESS → Deserialize normally
        return response.bodyToMono(ProductResponse.class).doOnNext(System.out::println);
    }



}
