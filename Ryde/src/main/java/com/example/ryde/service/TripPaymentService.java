package com.example.ryde.service;

import com.example.ryde.model.TripPayment;

import java.util.List;

public interface TripPaymentService {
    void saveTripPayment(TripPayment tripPayment);
    List<TripPayment> getAllTripPayments();
    TripPayment getPaymentById(Long id);
    List<TripPayment> getPaymentByUserId(Long userId);
}