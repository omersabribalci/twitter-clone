package com.wtech.twitter.service;

import com.wtech.twitter.dto.UserResponse;
import com.wtech.twitter.entity.User;
import java.util.UUID;

public interface UserService {
    UserResponse findById(UUID id);
    UserResponse findByUserName(String userName);
    User findUserEntityByUserName(String userName);

}
