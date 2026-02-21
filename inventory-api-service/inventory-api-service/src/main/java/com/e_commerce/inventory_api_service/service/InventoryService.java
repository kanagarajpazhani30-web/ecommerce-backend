package com.e_commerce.inventory_api_service.service;

import com.e_commerce.inventory_api_service.dto.InventoryRequest;
import com.e_commerce.inventory_api_service.entity.Inventory;
import com.e_commerce.inventory_api_service.repo.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public Mono<Inventory> getInventory(Long productId) {
        return inventoryRepository.findById(productId);
    }

    public Mono<Inventory> createInventory(InventoryRequest req) {
        Inventory inventory = Inventory.builder()
                .productId(req.getProductId())
                .totalQuantity(req.getTotalQuantity())
                .availableQuantity(req.getTotalQuantity())
                .reservedQuantity(0)
                .updatedAt(Instant.now())
                .build();

        return inventoryRepository.save(inventory);
    }
}
