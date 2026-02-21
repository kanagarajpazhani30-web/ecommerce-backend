package com.e_commerce.inventory_api_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InventoryRequest {

    @NotBlank(message = "Product ID is required")
    private Long productId;

    @NotNull(message = "Total quantity is required")
    @Min(value = 0, message = "Quantity cannot be negative")
    private Integer totalQuantity;
}