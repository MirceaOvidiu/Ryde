package com.example.ryde.mapper;

import com.example.ryde.dto.LoginDto;
import com.example.ryde.model.LoginInfo;

public class LoginMapper {

    public static LoginDto mapToLoginDto(LoginInfo loginInfo) {
        return new LoginDto(loginInfo.getUsername(), loginInfo.getPassword());
    }

    public static LoginInfo mapToLoginInfo(LoginDto loginDto) {
        return new LoginInfo(loginDto.getUsername(), loginDto.getPassword());
    }
}
