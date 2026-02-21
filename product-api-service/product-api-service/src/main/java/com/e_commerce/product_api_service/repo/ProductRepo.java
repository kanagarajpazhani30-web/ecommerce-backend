package com.e_commerce.product_api_service.repo;

import com.e_commerce.product_api_service.entities.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepo extends ReactiveCrudRepository<Product, Long> {
    Flux<Product> findByCategory(String category, Pageable pageable);

    Flux<Product> findByCategoryAndPrice(String category, Integer price);

    Flux<Product> findByCategoryAndPriceGreaterThan(String category, Integer price);
}
