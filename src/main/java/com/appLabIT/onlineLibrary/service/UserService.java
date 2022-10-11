package com.appLabIT.onlineLibrary.service;

import com.appLabIT.onlineLibrary.model.User;
import com.appLabIT.onlineLibrary.model.dto.UserDto;

import java.util.Optional;

public interface UserService {
    User addUser(UserDto userDto);

    void deleteUser(Integer userId);

    Optional<User> updateUser(Integer userId, UserDto userDto);
}
