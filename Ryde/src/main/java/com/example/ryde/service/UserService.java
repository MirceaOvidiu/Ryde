package com.example.ryde.service;

import com.example.ryde.dto.LoginDto;
import com.example.ryde.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto loginUser(LoginDto loginDto);
}
