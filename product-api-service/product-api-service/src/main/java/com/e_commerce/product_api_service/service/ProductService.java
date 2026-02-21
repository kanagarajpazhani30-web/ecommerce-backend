package com.e_commerce.product_api_service.service;

import com.e_commerce.product_api_service.entities.Product;
import com.e_commerce.product_api_service.exception.ProductNotFoundException;
import com.e_commerce.product_api_service.repo.ProductRepo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Pageable;


@Service
public class ProductService {
    private final ProductRepo productRepo;
    private final ReactiveRedisTemplate<String,Object> reactiveRedisTemplate;
    private final ObjectMapper mapper = new ObjectMapper();
    public ProductService(ProductRepo productRepo, ReactiveRedisTemplate<String, Object> reactiveRedisTemplate) {
        this.productRepo = productRepo;
        this.reactiveRedisTemplate = reactiveRedisTemplate;
    }


    @PostConstruct
    public void insertDB(){
        productRepo.count().flatMap(aLong ->
                {
                    productRepo.deleteAll();
                    if(aLong==0) {
                        return insertSampleProduct();
                    }
                    return Mono.empty();
                })
                ;

    }
    public Mono<Void> insertSampleProduct(){
        List<String> categories = List.of(
                "Mobiles", "Laptops", "Tablets", "Headphones", "Cameras",
                "Watches", "Shoes", "Books", "Clothes", "Gaming"
        );
        List<Product> sampleProductList=new ArrayList<>();
            for (String category : categories) {
                for (int i = 0; i < 100000; i++) {
                    sampleProductList.add(
                            Product.builder()
                                    .name(category + " - Item " + i)
                                    .description("Sample description for " + category + " Item " + i)
                                    .price(1000.0 + i)
                                    .stock(50 + i)
                                    .category(category)
                                    .build()
                    );
                }
            }
            return productRepo.saveAll(sampleProductList).then();


    }


    public Mono<Product> getProducts(long id) {
        return productRepo.findById(id).switchIfEmpty(Mono.error(new ProductNotFoundException("Product not found "+id)));
    }

    public Flux<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Flux<Product> searchByCategory(String category,int page,int size) {
        return productRepo.findAll()
                .filter(product ->
                        product.getCategory() != null &&
                                product.getCategory().equalsIgnoreCase(category)
                );
    }

    public Mono<List<Product>> search(String category, Integer price, int page, int size) {

        String key = "PRODUCTS:CATEGORY:" + category +
                ":PRICE:" + (price == null ? "NONE" : price) +
                ":PAGES:" + page +
                ":SIZE:" + size;

        return reactiveRedisTemplate.opsForValue()
                .get(key)
                .map(obj -> (List<Product>) obj)       // Cast safely
                .switchIfEmpty(
                        fetchFromDb(category, price, page, size)
                                .flatMap(list ->
                                        reactiveRedisTemplate.opsForValue()
                                                .set(key, list)
                                                .thenReturn(list)
                                )
                );
    }

    private String toJson(List<Product> list) {
        try {
            return mapper.writeValueAsString(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<Product> toProductList(String json) {
        try {
            return mapper.readValue(json, new TypeReference<>() {
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public Mono<List<Product>> fetchFromDb(String category, Integer price, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        if (price == null) {
            return productRepo.findByCategory(category, pageable).skip((long) page *size).take(size).collectList();
        }
        return productRepo.findByCategoryAndPriceGreaterThan(category, price).skip((long) page *size).take(size).collectList();
    }
}
