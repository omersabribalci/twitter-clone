package com.wtech.twitter.service;

import com.wtech.twitter.dto.*;

public interface AuthService {
    UserResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
}
