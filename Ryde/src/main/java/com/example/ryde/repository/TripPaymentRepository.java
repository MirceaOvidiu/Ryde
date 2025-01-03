package com.example.ryde.repository;

import com.example.ryde.model.TripPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripPaymentRepository extends JpaRepository<TripPayment, Long> {
}