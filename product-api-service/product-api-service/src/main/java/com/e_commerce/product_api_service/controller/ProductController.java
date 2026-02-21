package com.e_commerce.product_api_service.controller;


import com.e_commerce.product_api_service.dto.ProductSearchCriteria;
import com.e_commerce.product_api_service.entities.Product;
import com.e_commerce.product_api_service.service.ProductSearchService;
import com.e_commerce.product_api_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;
    private final ProductSearchService productSearchService;

    public ProductController(ProductService productService, ProductSearchService productSearchService) {
        this.productService = productService;
        this.productSearchService = productSearchService;
    }
    @Autowired
    private DatabaseClient databaseClient;

    @GetMapping("/search")
    public Flux<Product> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String seller) {

        ProductSearchCriteria criteria =
                ProductSearchCriteria.builder()
                        .name(name)
                        .category(category)
                        .minPrice(minPrice)
                        .maxPrice(maxPrice)
                        .seller(seller)
                        .build();

        return productSearchService.search(criteria);
    }



    @GetMapping("/{id}")
    private Mono<Product> getProductDetails(@PathVariable int id){
        return  productService.getProducts(id);
    }
    @GetMapping
    private Flux<Product> getAllProducts(){
        return productService.getAllProducts();
    }

//    @ExceptionHandler(RuntimeException.class)
//    private Mono<ResponseEntity<?>> handleProductNotFoundException(RuntimeException exception){
//        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage()));
//    }
//
//    private Mono<ResponseEntity<?>> handleProductNotFoundException2(RuntimeException exception){
//        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage()));
//    }
}
