package com.example.ryde.service;

import com.example.ryde.model.TripPayment;

import java.util.List;

public interface TripPaymentService {
    TripPayment saveTripPayment(TripPayment tripPayment);
    List<TripPayment> getAllTripPayments();
    TripPayment getTripPaymentById(Long id);
}