package com.e_commerce.inventory_api_service.common_dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {

    private Long orderId;
    private Long productId;
    private Integer quantity;
    private String status; // CONFIRMED, CANCELLED
}
