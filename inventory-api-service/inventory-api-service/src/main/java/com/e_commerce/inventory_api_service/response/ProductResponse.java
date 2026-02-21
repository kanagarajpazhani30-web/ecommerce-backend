package com.e_commerce.inventory_api_service.response;


import lombok.Data;

@Data
public class ProductResponse {

    private Long id;

    private String name;

    private Double price;

    private String description;

    private String category;

    private Double ratings = 0.0;

    private String seller;

    private Integer stock;


    private Integer numOfReviews = 0;
}
