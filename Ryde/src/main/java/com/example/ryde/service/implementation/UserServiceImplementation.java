package com.example.ryde.service.implementation;

import com.example.ryde.dto.UserDto;
import com.example.ryde.mapper.UserMapper;
import com.example.ryde.model.MyUser;
import com.example.ryde.repository.UserRepository;
import com.example.ryde.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        MyUser user = UserMapper.mapToUser(userDto);
        MyUser savedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }
}
