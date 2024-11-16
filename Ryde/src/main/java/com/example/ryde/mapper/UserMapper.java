package com.example.ryde.mapper;

import com.example.ryde.dto.UserDto;
import com.example.ryde.model.MyUser;


public class UserMapper {

    public static UserDto mapToUserDto(MyUser user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getIban(),
                user.getRole()
        );
    }

    public static MyUser mapToUser(UserDto userDto) {
        return new MyUser(
                userDto.getId(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getEmail(),
                userDto.getIban()
        );
    }

}
