package com.example.ryde;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public boolean login(String username, String password) {
        Customer customer = customerRepository.findByUsernameAndPassword(username, password);
        return customer != null;
    }
}