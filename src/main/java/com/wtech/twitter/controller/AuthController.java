package com.wtech.twitter.controller;

import com.wtech.twitter.dto.*;
import com.wtech.twitter.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ApiResponse<UserResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        UserResponse userResponse = authService.register(registerRequest);
        return ApiResponse.success("User created successfully!", userResponse);
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = authService.login(loginRequest);
        return ApiResponse.success("Logged in successfully!", loginResponse);
    }
}
