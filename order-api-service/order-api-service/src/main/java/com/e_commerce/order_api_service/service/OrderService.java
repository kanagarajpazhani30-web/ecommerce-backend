package com.e_commerce.order_api_service.service;

import com.e_commerce.order_api_service.client.InventoryClient;
import com.e_commerce.order_api_service.client.ProductClient;
import com.e_commerce.order_api_service.dto.OrderEvent;
import com.e_commerce.order_api_service.dto.OrderRequest;
import com.e_commerce.order_api_service.entity.Order;
import com.e_commerce.order_api_service.repo.OrderRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class OrderService {

    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;
    private final InventoryClient inventoryClient;
    public OrderService(ProductClient productClient, OrderRepository orderRepository, KafkaTemplate<String, OrderEvent> kafkaTemplate, InventoryClient inventoryClient) {
        this.productClient = productClient;
        this.orderRepository = orderRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.inventoryClient = inventoryClient;
    }

    public Mono<Order> placeOrder(OrderRequest request) {

        return inventoryClient.getInventory(request.getProductId())

                .switchIfEmpty(
                        Mono.error(new RuntimeException("Inventory not found"))
                )

                .flatMap(inventoryResponse -> {

                    if (inventoryResponse.getAvailableQuantity() < request.getQuantity()) {
                        return Mono.error(
                                new RuntimeException("Insufficient stock")
                        );
                    }

                    return productClient.getProduct(request.getProductId());
                })

                .switchIfEmpty(
                        Mono.error(new RuntimeException("Product not found"))
                )

                .flatMap(product -> {

                    Double totalPrice =
                            product.getPrice() * request.getQuantity();

                    Order order = Order.builder()
                            .productId(product.getId())
                            .productName(product.getName())
                            .price(product.getPrice())
                            .quantity(request.getQuantity())
                            .totalPrice(totalPrice)
                            .status("CONFIRMED")
                            .build();

                    return orderRepository.save(order);
                })

                .flatMap(savedOrder -> {

                    OrderEvent event = OrderEvent.builder()
                            .orderId(savedOrder.getId())
                            .productId(savedOrder.getProductId())
                            .quantity(savedOrder.getQuantity())
                            .status(savedOrder.getStatus())
                            .build();

                    kafkaTemplate.send("order-events", event);

                    return Mono.just(savedOrder);
                });


    }

}
