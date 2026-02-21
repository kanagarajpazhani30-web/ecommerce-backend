package com.e_commerce.order_api_service.controller;

import com.e_commerce.order_api_service.dto.OrderRequest;
import com.e_commerce.order_api_service.entity.Order;
import com.e_commerce.order_api_service.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/order/")
public class OrderController {
   private final OrderService orderService;
    public OrderController(OrderService orderService){
        this.orderService=orderService;
    }

    @PostMapping
    public Mono<Order> placeOrder(
            @RequestBody OrderRequest request) {

        return orderService.placeOrder(request);
    }
}
