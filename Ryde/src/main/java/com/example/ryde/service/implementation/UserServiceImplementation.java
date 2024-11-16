package com.example.ryde.service.implementation;

import com.example.ryde.dto.UserDto;
import com.example.ryde.mapper.UserMapper;
import com.example.ryde.model.MyUser;
import com.example.ryde.repository.UserRepository;
import com.example.ryde.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;



import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        MyUser user = UserMapper.mapToUser(userDto);
        user.setRole("USER");
        // SHA 256 hashing
        // In order to avoid the use of spring-security which forces request auth
        user.setPassword(hashPassword(user.getPassword()));
        user.setId((UUID.randomUUID()).getLeastSignificantBits() & Long.MAX_VALUE);
        MyUser savedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(encodedHash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    private String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
