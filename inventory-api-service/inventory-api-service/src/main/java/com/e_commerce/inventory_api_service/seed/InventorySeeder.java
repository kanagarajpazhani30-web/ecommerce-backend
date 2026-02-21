package com.e_commerce.inventory_api_service.seed;

import com.e_commerce.inventory_api_service.client.ProductClient;
import com.e_commerce.inventory_api_service.dto.ProductDto;
import com.e_commerce.inventory_api_service.entity.Inventory;
import com.e_commerce.inventory_api_service.repo.InventoryRepository;
import com.e_commerce.inventory_api_service.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class InventorySeeder implements CommandLineRunner {

    private final ProductClient productClient;
    private final InventoryRepository inventoryRepository;

    @Override
    public void run(String... args) throws Exception {

        List<Inventory> inventories =
                productClient.getAllProducts()
                        .map(product -> Inventory.builder()
                                .productId(product.getId())
                                .totalQuantity(product.getStock())
                                .availableQuantity(product.getStock())
                                .reservedQuantity(0)
                                .updatedAt(Instant.now())
                                .build()
                        )
                        .collectList()
                        .block();

        assert inventories != null;
        //inventoryRepository.saveAll(inventories).subscribe();


    }
}
