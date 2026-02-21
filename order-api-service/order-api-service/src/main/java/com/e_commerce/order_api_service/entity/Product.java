package com.e_commerce.order_api_service.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    private Long id;

    private String name;

    private Double price;

    private String description;

    private String category;

    private Double ratings;

    private String seller;

    private Integer stock;

    private Integer numOfReviews;
}
