package com.e_commerce.user_api_service;

import com.e_commerce.user_api_service.dto.RegisterRequest;
import com.e_commerce.user_api_service.model.User;
import com.e_commerce.user_api_service.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Mono<User> register(RegisterRequest request) {

        return userRepository.existsByEmail(request.getEmail())
                .flatMap(exists -> {
                    if ((Boolean) exists) {
                        return Mono.error(new RuntimeException("Email already exists"));
                    }

                    User user = User.builder()
                            .username(request.getUsername())
                            .email(request.getEmail())
                            .password(passwordEncoder.encode(request.getPassword())) // 🔐 IMPORTANT
                            .roles("USER")
                            .enabled(true)
                            .accountNonLocked(true)
                            .createdAt(LocalDateTime.now())
                            .updatedAt(LocalDateTime.now())
                            .build();

                    return userRepository.save(user);
                });
    }
}
