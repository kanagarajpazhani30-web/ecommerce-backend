package com.e_commerce.product_api_service.exception;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Builder
@Data
public class ErrorMessage {
    private String message;
    private int statusCode;
    private LocalDate localDate;
}
