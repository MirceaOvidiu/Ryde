package com.example.ryde.service;

import com.example.ryde.dto.LoginDto;
import com.example.ryde.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto loginUser(LoginDto loginDto);
    UserDto getMyUserById(Long id);
    List<UserDto> getAllUsers();
}
