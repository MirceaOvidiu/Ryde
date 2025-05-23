/** implementation class for the TripPaymentService interface
 * @author Dodi Mircea Ovidiu
 * @version 2 Jan 2025
 */

package com.example.ryde.service.implementation;

import com.example.ryde.dto.UserTripMetricsDTO;
import com.example.ryde.model.TripPayment;
import com.example.ryde.dto.PaymentMetricDTO;
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
    public void saveTripPayment(TripPayment tripPayment) {
        tripPaymentRepository.save(tripPayment);
    }

    @Override
    public List<TripPayment> getAllTripPayments() {
        return tripPaymentRepository.findAll();
    }

    @Override
    public TripPayment getPaymentById(Long id) {
        return tripPaymentRepository.findById(id).orElse(null);
    }

    @Override
    public List<TripPayment> getPaymentByUserId(Long userId) {
        return tripPaymentRepository.findByUserId(userId);
    }

    @Override
    public List<PaymentMetricDTO> getPaymentMetrics(Long userId) { return tripPaymentRepository.findPaymentMetrics(userId);
    }

    @Override
    public List<UserTripMetricsDTO> getUserPaymentMetrics(Long userId) {return tripPaymentRepository.findUserTripMetrics(userId);}

    @Override
    public List<Object[]> findUserWithMostUnpaidTrips() { return tripPaymentRepository.findUserWithMostUnpaidTrips();}

    @Override
    public List<Object[]> findUserSpendingOnTrips() { return tripPaymentRepository.findUserSpendingOnTrips();}

    @Override
    public List<String> findUsernamesForUnpaidTrips() { return tripPaymentRepository.findUsernamesForUnpaidTrips(); }

}