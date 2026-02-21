package com.e_commerce.product_api_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    private Long id;

    private String name;

    private Double price;

    private String description;

    private String category;

    @Builder.Default
    private Double ratings = 0.0;

    private String seller;

    private Integer stock;

    @Builder.Default
    private Integer numOfReviews = 0;

    private List<ProductImageDto> images;

    private List<ProductReviewDto> reviews;
}
