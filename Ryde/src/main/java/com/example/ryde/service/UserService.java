package com.example.ryde.service;

import com.example.ryde.model.MyUser;
import com.example.ryde.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<MyUser> getAllUsers() {
        return userRepository.findAll();
    }

    public void saveUser(MyUser user) {
        user.setPassword((user.getPassword()));
        userRepository.save(user);
    }

    public MyUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public MyUser findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}