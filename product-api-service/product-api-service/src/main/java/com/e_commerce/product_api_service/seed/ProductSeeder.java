package com.e_commerce.product_api_service.seed;

import com.e_commerce.product_api_service.entities.Product;
import com.e_commerce.product_api_service.repo.ProductRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import java.util.List;

@Component
public class ProductSeeder implements CommandLineRunner {

    private final ProductRepo productRepo;

    public ProductSeeder(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public void run(String... args) {
                        List<Product> demoProducts = List.of(
                                Product.builder()
                                        .name("Apple iPhone 15")
                                        .price(799.0)
                                        .description("SmartPhone with A16 Chip")
                                        .category("Phones")
                                        .ratings(4.8)
                                        .seller("Amazon")
                                        .stock(5)
                                        .numOfReviews(0)
                                        .build(),

                                Product.builder()
                                        .name("Samsung Galaxy S23")
                                        .price(749.0)
                                        .description("Flagship Android with Snapdragon 8 Gen 2")
                                        .category("Phones")
                                        .ratings(4.7)
                                        .seller("Flipkart")
                                        .stock(8)
                                        .build(),

                                Product.builder()
                                        .name("Dell XPS 13")
                                        .price(1199.0)
                                        .description("Ultrabook with Intel i7 and 16GB RAM")
                                        .category("Laptops")
                                        .ratings(4.6)
                                        .seller("Amazon")
                                        .stock(3)
                                        .numOfReviews(0)
                                        .build(),

                                Product.builder()
                                        .name("Sony WH-1000XM5")
                                        .price(349.0)
                                        .description("Noise Cancelling Wireless Headphones")
                                        .category("Audio")
                                        .ratings(4.9)
                                        .seller("Croma")
                                        .stock(10)
                                        .numOfReviews(0)
                                        .build(),

                                Product.builder()
                                        .name("Apple MacBook Air M2")
                                        .price(999.0)
                                        .description("Lightweight laptop with M2 chip")
                                        .category("Laptops")
                                        .ratings(4.8)
                                        .seller("Amazon")
                                        .stock(4)
                                        .build(),

                                Product.builder()
                                        .name("Canon EOS R50")
                                        .price(679.0)
                                        .description("Mirrorless Camera with 24MP sensor")
                                        .category("Cameras")
                                        .ratings(4.5)
                                        .seller("Reliance Digital")
                                        .stock(2)
                                        .numOfReviews(0)
                                        .build(),

                                Product.builder()
                                        .name("OnePlus 11")
                                        .price(699.0)
                                        .description("Flagship killer with Snapdragon 8 Gen 2")
                                        .category("Phones")
                                        .ratings(4.6)
                                        .seller("Amazon")
                                        .stock(6)
                                        .numOfReviews(0)
                                        .build(),

                                Product.builder()
                                        .name("JBL Flip 6")
                                        .price(129.0)
                                        .description("Portable Bluetooth Speaker")
                                        .category("Audio")
                                        .ratings(4.4)
                                        .seller("Flipkart")
                                        .stock(12)
                                        .numOfReviews(0)
                                        .build(),

                                Product.builder()
                                        .name("HP Envy x360")
                                        .price(899.0)
                                        .description("2-in-1 Laptop with Ryzen 7")
                                        .category("Laptops")
                                        .ratings(4.7)
                                        .seller("Amazon")
                                        .stock(5)
                                        .numOfReviews(0)
                                        .build(),

                                Product.builder()
                                        .name("Google Pixel 7")
                                        .price(649.0)
                                        .description("Clean Android experience with Tensor chip")
                                        .category("Phones")
                                        .ratings(4.5)
                                        .seller("Flipkart")
                                        .stock(7)
                                        .numOfReviews(0)
                                        .build()
                        );

                         productRepo.saveAll(demoProducts)
                                .then(Mono.fromRunnable(() ->
                                        System.out.println("✅ Seeded demo products.")
                                ));

    }
}
