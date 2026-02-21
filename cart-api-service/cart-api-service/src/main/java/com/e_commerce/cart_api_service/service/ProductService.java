package com.e_commerce.cart_api_service.service;

import com.e_commerce.cart_api_service.client.ProductClientConfig;
import com.e_commerce.cart_api_service.model.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    private final ProductClientConfig productClient;

    public ProductService(ProductClientConfig productClient) {
        this.productClient = productClient;
    }

    public Mono<ProductResponse> getProductDetails(long productId){
        return productClient.getProductDetails(productId);
    }
}
