package com.wtech.twitter.service.impl;

import com.wtech.twitter.dto.*;
import com.wtech.twitter.dto.converter.UserDtoConverter;
import com.wtech.twitter.entity.User;
import com.wtech.twitter.exceptions.TwitterException;
import com.wtech.twitter.repository.UserRepository;
import com.wtech.twitter.security.JwtUtil;
import com.wtech.twitter.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDtoConverter userDtoConverter;

    public AuthServiceImpl(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       JwtUtil jwtUtil, UserDtoConverter userDtoConverter) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDtoConverter = userDtoConverter;
    }

    public UserResponse register(RegisterRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return userDtoConverter.convertToDto(user);

    }

    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserName(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByUserName(request.getUserName())
                .orElseThrow(() -> new TwitterException("User not found", HttpStatus.NOT_FOUND));

        String token = jwtUtil.generateToken(request.getUserName());
        UserResponse userResponse = userDtoConverter.convertToDto(user);
        return new LoginResponse(token, userResponse);
    }
}

