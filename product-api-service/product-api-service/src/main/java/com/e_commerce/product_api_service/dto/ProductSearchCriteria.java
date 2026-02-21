package com.e_commerce.product_api_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductSearchCriteria {

    private String name;
    private String category;
    private Double minPrice;
    private Double maxPrice;
    private String seller;
    private Double minRating;
    private Boolean inStock;
}

