package com.example.ryde.Dto;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerID(rs.getString("customerID"));
        customer.setName(rs.getString("name"));
        customer.setSurname(rs.getString("surname"));
        customer.setGender(rs.getString("gender").charAt(0));
        customer.setIban(rs.getString("iban"));
        customer.setEmail(rs.getString("email"));
        customer.setUsername(rs.getString("username"));
        customer.setPassword(rs.getString("password"));
        customer.setRole(rs.getString("role"));
        return customer;
    }
}