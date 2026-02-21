package com.e_commerce.order_api_service.client;

import com.e_commerce.order_api_service.response.InventoryResponse;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import io.github.resilience4j.reactor.retry.RetryOperator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class InventoryClient {

    private final WebClient webClient;
    private final CircuitBreaker circuitBreaker;

    public InventoryClient(WebClient.Builder builder,
                           CircuitBreakerRegistry circuitBreakerRegistry) {

        this.webClient = builder.baseUrl("http://localhost:8092").build();

        this.circuitBreaker =
                circuitBreakerRegistry.circuitBreaker("inventoryService");

        // ✅ ADD THESE LOG LISTENERS
        this.circuitBreaker.getEventPublisher()

                .onStateTransition(event ->
                        System.out.println("CircuitBreaker state changed: "
                                + event.getStateTransition()))

                .onSuccess(event ->
                        System.out.println("CircuitBreaker success: "
                                + event.getElapsedDuration().toMillis() + " ms"))

                .onError(event ->
                        System.out.println("CircuitBreaker error: "
                                + event.getThrowable().getMessage()))

                .onCallNotPermitted(event ->
                        System.out.println("CircuitBreaker OPEN - Call blocked"));
    }

    public Mono<InventoryResponse> getInventory(Long productId) {

        return webClient.get()
                .uri("/api/inventory/{id}", productId)
                .retrieve()
                .bodyToMono(InventoryResponse.class)

                .doOnSubscribe(sub ->
                        System.out.println("Calling Inventory Service productId=" + productId)
                )

                .transformDeferred(
                        CircuitBreakerOperator.of(circuitBreaker)
                )

                .doOnSuccess(res ->
                        System.out.println("Inventory service response received")
                )

                .doOnError(ex ->
                        System.out.println("Inventory service failed: " + ex.getMessage())
                )

                .onErrorResume(ex -> {
                    System.out.println("Fallback executed");
                    return Mono.empty();
                });
    }
}
