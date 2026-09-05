package com.wtech.twitter.dto.converter;

import com.wtech.twitter.dto.UserResponse;
import com.wtech.twitter.entity.User;
import org.springframework.stereotype.Component;

public class UserDtoConverter {
    public static UserResponse convertToDto(User user) {
        if (user == null) {
            return null;
        }

        return new UserResponse(user.getEmail(), user.getUserName(), user.getName());
    }
}
