package com.example.ryde;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    @Query(value = "SELECT * FROM customer WHERE username = :username AND password = :password", nativeQuery = true)
    Customer findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Query(value = "SELECT * FROM customer WHERE username = :username", nativeQuery = true)
    Customer findByUsername(@Param("username") String username);
}