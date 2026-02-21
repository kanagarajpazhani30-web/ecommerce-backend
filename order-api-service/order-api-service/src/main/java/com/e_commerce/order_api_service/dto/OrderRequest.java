package com.e_commerce.order_api_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@NoArgsConstructor
public class OrderRequest {
    private String userId;

    private Long productId;

    private Integer quantity;
}

