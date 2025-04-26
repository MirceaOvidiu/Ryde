/** implementation class for the userService interface
 * @author Dodi Mircea Ovidiu
 * @version 3 Jan 2025
 */

package com.example.ryde.service.implementation;

import com.example.ryde.dto.LoginDto;
import com.example.ryde.dto.UserDto;
import com.example.ryde.mapper.UserMapper;
import com.example.ryde.model.MyUser;
import com.example.ryde.model.Trip;
import com.example.ryde.model.TripPayment;
import com.example.ryde.service.UserService;
import jakarta.persistence.EntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {
    private final JdbcTemplate jdbcTemplate;
    private final EntityManager entityManager;

    public UserServiceImplementation(JdbcTemplate jdbcTemplate, EntityManager entityManager) {
        this.jdbcTemplate = jdbcTemplate;
        this.entityManager = entityManager;
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
            return UserMapper.mapToUserDto(user);
        } else {
            // Throw an exception if the username or password is invalid
            throw new RuntimeException("Invalid username or password");
        }
    }

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        String checkSql = "SELECT COUNT(*) FROM \"user\" WHERE username = ?";
        Integer count = jdbcTemplate.queryForObject(checkSql, Integer.class, userDto.getUsername());

        if (count > 0) {
            throw new RuntimeException("Username already exists");
        }

        if (!userDto.getUsername().matches("^[a-zA-Z0-9]+$")) {
            throw new RuntimeException("Username must contain only alphanumeric characters");
        }

//        if (!userDto.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$")) {
//            throw new RuntimeException("Password must contain at least one lowercase letter, one uppercase letter, one number, and one special character");
//        }


        MyUser userDTO = UserMapper.mapToUser(userDto);
        userDTO.setRole("USER");
        userDTO.setPassword(hashPassword(userDTO.getPassword()));

        ///  SQL insert for the user
        String insertSql = "INSERT INTO \"user\" (username, password, email, iban, role) " +
                "VALUES (:username, :password, :email, :iban, :role)";
        entityManager.createNativeQuery(insertSql)
                .setParameter("username", userDTO.getUsername())
                .setParameter("password", userDTO.getPassword())
                .setParameter("email", userDTO.getEmail())
                .setParameter("iban", userDTO.getIban())
                .setParameter("role", userDTO.getRole())
                .executeUpdate();

        return UserMapper.mapToUserDto(userDTO);
    }

    @Transactional
    public void forgetMe(Long id) {
        // Find and delete trip payments
        List<TripPayment> tripPayments = entityManager.createQuery("DELETE FROM TripPayment tp WHERE tp.userId= :userId", TripPayment.class)
                .setParameter("userId", id)
                .getResultList();
        for (TripPayment tripPayment : tripPayments) {
            entityManager.remove(tripPayment);
        }

        // Find and delete trips
        List<Trip> trips = entityManager.createQuery("DELETE FROM Trip t WHERE t.customer = :userId", Trip.class)
                .setParameter("userId", id)
                .getResultList();
        for (Trip trip : trips) {
            entityManager.remove(trip);
        }

        // Find and delete user
        MyUser user = entityManager.find(MyUser.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    ///  password hashing functions
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