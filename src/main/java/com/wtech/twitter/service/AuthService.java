package com.wtech.twitter.service;

import com.wtech.twitter.dto.*;
import com.wtech.twitter.entity.User;

public interface AuthService {
    UserResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
}
