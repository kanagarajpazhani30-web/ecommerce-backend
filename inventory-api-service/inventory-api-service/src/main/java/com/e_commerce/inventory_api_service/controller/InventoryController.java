package com.e_commerce.inventory_api_service.controller;

import com.e_commerce.inventory_api_service.dto.InventoryRequest;
import com.e_commerce.inventory_api_service.entity.Inventory;
import com.e_commerce.inventory_api_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/inventory/")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping
    public Mono<Inventory> createInventory(@RequestBody InventoryRequest request) {
        return inventoryService.createInventory(request);
    }

    @GetMapping("{productId}")
    public Mono<Inventory> getInventory(@PathVariable Long productId) {
        return inventoryService.getInventory(productId);
    }
}
