package com.example.ryde.service.implementation;

import com.example.ryde.model.TripPayment;
import com.example.ryde.repository.TripPaymentRepository;
import com.example.ryde.service.TripPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripPaymentServiceImplementation implements TripPaymentService {

    private final TripPaymentRepository tripPaymentRepository;

    @Autowired
    public TripPaymentServiceImplementation(TripPaymentRepository tripPaymentRepository) {
        this.tripPaymentRepository = tripPaymentRepository;
    }

    @Override
    public TripPayment saveTripPayment(TripPayment tripPayment) {
        return tripPaymentRepository.save(tripPayment);
    }

    @Override
    public List<TripPayment> getAllTripPayments() {
        return tripPaymentRepository.findAll();
    }

    @Override
    public TripPayment getTripPaymentById(Long id) {
        return tripPaymentRepository.findById(id).orElse(null);
    }
}