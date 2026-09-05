package com.wtech.twitter.service.impl;

import com.wtech.twitter.dto.UserResponse;
import com.wtech.twitter.dto.converter.UserDtoConverter;
import com.wtech.twitter.entity.User;
import com.wtech.twitter.exceptions.TwitterException;
import com.wtech.twitter.repository.UserRepository;
import com.wtech.twitter.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserResponse findById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new TwitterException("User does not exist with this ID: " + id, HttpStatus.NOT_FOUND)
        );

        return UserDtoConverter.convertToDto(user);
    }

    @Override
    public UserResponse findByUserName(String userName) {
        User user = userRepository.findByUserName(userName).orElseThrow(
                () -> new TwitterException("User does not exist with this username: " + userName, HttpStatus.NOT_FOUND)
        );

        return UserDtoConverter.convertToDto(user);

    }

    @Override
    public User findUserEntityByUserName(String userName) {
        return userRepository.findByUserName(userName).orElseThrow(
                () -> new TwitterException("User does not exist with this username: " + userName, HttpStatus.NOT_FOUND)
        );
    }
}
