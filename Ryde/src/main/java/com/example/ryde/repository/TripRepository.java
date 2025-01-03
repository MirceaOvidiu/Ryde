package com.example.ryde.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ryde.model.Trip;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByCustomer(Long customerId);
}
