package com.e_commerce.cart_api_service.controller;

import com.e_commerce.cart_api_service.model.ProductResponse;
import com.e_commerce.cart_api_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    ProductService productService;
    @GetMapping("/{id}")
    private Mono<ProductResponse>getProductDetails(@PathVariable long id){
        return productService.getProductDetails(id).flatMap(response->
                 Mono.just(new ProductResponse()));
    }
}
