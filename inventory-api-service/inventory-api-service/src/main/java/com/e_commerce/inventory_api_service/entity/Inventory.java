package com.e_commerce.inventory_api_service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("inventory")
public class Inventory {

    @Id
    private Long id;   // ✅ Primary key

    private Long productId;

    private Integer totalQuantity;
    private Integer availableQuantity;
    private Integer reservedQuantity;

    private Instant updatedAt;
}
