package com.e_commerce.product_api_service.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductImageDto {
    private String url;

    public ProductImageDto(String url) {
        super();
        this.url = url;
    }

}
