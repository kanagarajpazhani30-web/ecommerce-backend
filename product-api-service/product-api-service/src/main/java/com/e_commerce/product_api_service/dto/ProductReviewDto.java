package com.e_commerce.product_api_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductReviewDto {
    @NotNull(message = "Product ID is required")
    private Long productId;

    @NotBlank(message = "Comment cannot be blank.")
    private String comment;
    @NotNull(message = "Rating is required")

    private Double rating;
}