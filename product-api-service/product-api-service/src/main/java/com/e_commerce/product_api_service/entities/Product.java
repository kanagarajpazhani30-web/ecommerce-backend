package com.e_commerce.product_api_service.entities;

import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

import jakarta.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("product")
@EntityScan
public class Product {

    @Id
    private Long id;

    @NotBlank(message = "Name field is required")
    private String name;

    @NotNull(message = "Price field is required")
    @PositiveOrZero(message = "Value must be zero or greater than zero")
    private Double price;

    private String description;

    @NotBlank(message = "Category field is required")
    private String category;

    @Builder.Default
    private Double ratings = 0.0;

    @NotBlank(message = "Seller field is required")
    private String seller;

    @NotNull(message = "Stock field is required")
    private Integer stock;

    @Builder.Default
    private Integer numOfReviews = 0;

}
