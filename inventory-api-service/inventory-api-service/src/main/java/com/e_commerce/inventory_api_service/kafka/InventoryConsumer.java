package com.e_commerce.inventory_api_service.kafka;

import com.e_commerce.inventory_api_service.common_dto.OrderEvent;
import com.e_commerce.inventory_api_service.entity.Inventory;
import com.e_commerce.inventory_api_service.repo.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class InventoryConsumer {
    private InventoryRepository inventoryRepository;
    @RetryableTopic(
            attempts = "3",
            backoff = @Backoff(delay = 3000),
            dltTopicSuffix = "-dlt"
    )
    @KafkaListener(topics = "order-events", groupId = "inventory-group")
    public void consume(OrderEvent event) {

        inventoryRepository
                .findByProductId(event.getProductId())
                .switchIfEmpty(Mono.error(
                        new RuntimeException("Product not found")
                ))
                .flatMap(inventory -> {

                    inventory.setAvailableQuantity(
                            inventory.getAvailableQuantity() - event.getQuantity()
                    );

                    inventory.setReservedQuantity(
                            inventory.getReservedQuantity() + event.getQuantity()
                    );

                    return inventoryRepository.save(inventory);
                })
                .doOnError(ex -> {
                    throw new RuntimeException(ex);
                })
                .subscribe();
    }

}
