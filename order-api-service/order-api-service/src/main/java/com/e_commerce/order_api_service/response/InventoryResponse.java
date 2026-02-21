package com.e_commerce.order_api_service.response;

import lombok.Data;

import java.time.Instant;

@Data
public class InventoryResponse {
    private Long productId;
    private Integer totalQuantity;
    private Integer availableQuantity;
    private Integer reservedQuantity;

    private Instant updatedAt;
}
