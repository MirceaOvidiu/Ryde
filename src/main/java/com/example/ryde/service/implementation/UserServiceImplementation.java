/** implementation class for the userService interface
 * @author Dodi Mircea Ovidiu
 * @version 3 Jan 2025
 */

package com.example.ryde.service.implementation;

import com.example.ryde.repository.UserRepository;
import com.example.ryde.dto.LoginDto;
import com.example.ryde.dto.UserDto;
import com.example.ryde.mapper.UserMapper;
import com.example.ryde.model.MyUser;
import com.example.ryde.service.UserService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {
    private final JdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;

    public UserServiceImplementation(JdbcTemplate jdbcTemplate, UserRepository userRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getAllUsers() {
        String sql = "SELECT * FROM \"user\"";
        List<MyUser> users = jdbcTemplate.query(sql, (rs, rowNum) -> {
            MyUser user = new MyUser();
            user.setId(rs.getLong("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getString("role"));
            user.setIban(rs.getString("iban"));
            user.setEmail(rs.getString("email"));
            return user;
        });

        return users.stream()
                .map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id) {
        String sql = "DELETE FROM \"user\" WHERE id = ?";
        int rows = jdbcTemplate.update(sql, id);
        if (rows == 0) {
            throw new RuntimeException("User not found");
        }
    }

    public UserDto loginUser(LoginDto loginDto) {
        String sql = "SELECT * FROM \"user\" WHERE username = ?";

        // Execute the query and map the result set to a MyUser object
        MyUser user = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            MyUser u = new MyUser();
            u.setId(rs.getLong("id"));
            u.setUsername(rs.getString("username"));
            u.setPassword(rs.getString("password"));
            u.setRole(rs.getString("role"));
            u.setIban(rs.getString("iban"));
            u.setEmail(rs.getString("email"));
            return u;
        }, loginDto.getUsername());

        // Check if the user exists and the password matches
        if (user != null && user.getPassword().equals(hashPassword(loginDto.getPassword()))) {
            // Convert MyUser to UserDto and return
            return UserMapper.mapToUserDto(user);
        } else {
            // Throw an exception if the username or password is invalid
            throw new RuntimeException("Invalid username or password");
        }
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        String sql = "SELECT COUNT(*) FROM \"user\" WHERE username = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, userDto.getUsername());

        if (count > 0) {
            throw new RuntimeException("Username already exists");
        }

        MyUser user = UserMapper.mapToUser(userDto);
        user.setRole("USER");
        // SHA 256 hashing
        // In order to avoid the use of spring-security which forces request auth
        user.setPassword(hashPassword(user.getPassword()));

        // This type of id generation involves JavaScript problems while handling big numbers
        // user.setId((UUID.randomUUID()).getLeastSignificantBits() & Long.MAX_VALUE);
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