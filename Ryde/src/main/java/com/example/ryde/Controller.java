package com.example.ryde;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class Controller {

    private final JdbcTemplate jdbcTemplate;

    public Controller(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/register")
    public String register(@RequestParam String name, @RequestParam String surname, @RequestParam char gender,
                           @RequestParam String iban, @RequestParam String email, @RequestParam String username,
                           @RequestParam String password) {
        String customerId = UUID.randomUUID().toString(); // Generate a unique customerID
        String sql = "INSERT INTO customer (customerId, name, surname, gender,iban, email, username, password, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        int result = jdbcTemplate.update(sql, customerId, name, surname, gender, iban, email, username, password, "customer");
        if (result > 0) {
            return "Customer registered successfully!";
        } else {
            return "Error registering customer!";
        }
    }
}