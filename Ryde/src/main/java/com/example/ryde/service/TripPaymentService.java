package com.example.ryde.service;

import com.example.ryde.model.TripPayment;

import java.util.List;

public interface TripPaymentService {
    void saveTripPayment(TripPayment tripPayment);
    List<TripPayment> getAllTripPayments();
    TripPayment getTripPaymentById(Long id);
}