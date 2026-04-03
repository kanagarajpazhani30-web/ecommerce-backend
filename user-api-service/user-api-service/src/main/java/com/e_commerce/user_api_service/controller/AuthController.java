package com.e_commerce.user_api_service.controller;

import com.e_commerce.user_api_service.UserService;
import com.e_commerce.user_api_service.config.JwtAuthenticationManager;
import com.e_commerce.user_api_service.config.JwtUtil;
import com.e_commerce.user_api_service.dto.LoginRequestDTO;
import com.e_commerce.user_api_service.dto.RegisterRequest;
import com.e_commerce.user_api_service.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final JwtAuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    final private UserService userService;
    AuthController(JwtAuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService userService){
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService=userService;
    }

    @PostMapping("/register")
    public Mono<ResponseEntity<?>> register(@RequestBody RegisterRequest request) {

        return userService.register(request)
                .map(user -> ResponseEntity.ok("Registered successfully.."));
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<Map<String, String>>> login(@RequestBody LoginRequestDTO request) {

        Authentication auth = new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        );

        return authenticationManager.authenticate(auth)
                .map(authentication -> {
                    String token = jwtUtil.generateToken(authentication.getName());
                    return ResponseEntity.ok(Map.of("token", token));
                });
    }
}
