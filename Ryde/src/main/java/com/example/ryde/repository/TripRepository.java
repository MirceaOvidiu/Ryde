package com.example.ryde.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ryde.model.Trip;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
}
