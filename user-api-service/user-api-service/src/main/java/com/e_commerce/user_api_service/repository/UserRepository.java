package com.e_commerce.user_api_service.repository;

import com.e_commerce.user_api_service.model.User;
import io.r2dbc.spi.Result;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {


    Mono<User> findByEmail(String email);

    Mono<Object> existsByEmail(String email);
}
