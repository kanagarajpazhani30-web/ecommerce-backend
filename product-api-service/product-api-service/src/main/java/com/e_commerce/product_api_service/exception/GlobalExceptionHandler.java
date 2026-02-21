package com.e_commerce.product_api_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    private Mono<ResponseEntity<ErrorMessage>> handleProductNotFound(ProductNotFoundException productNotFoundException) {
        ErrorMessage errorMessage=ErrorMessage.builder()
                .message(productNotFoundException.getMessage())
                .localDate(LocalDate.now())
                .statusCode(404)
                .build();
        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage));
    }
}
