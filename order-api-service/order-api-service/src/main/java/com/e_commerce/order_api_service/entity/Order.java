package com.e_commerce.order_api_service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Data
@Builder
@Table("orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    private Long id;

    private String userId;

    private Long productId;

    private String productName;

    private Double price;

    private Integer quantity;

    private Double totalPrice;

    private String status;
}
