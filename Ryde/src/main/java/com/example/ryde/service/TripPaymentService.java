/** interface class for interacting with the TripPayment entity
 * @author Dodi Mircea Ovidiu
 * @version 5 Jan 2025
 */

package com.example.ryde.service;

import com.example.ryde.dto.PaymentMetricDTO;
import com.example.ryde.dto.UserTripMetricsDTO;
import com.example.ryde.model.TripPayment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TripPaymentService {
    void saveTripPayment(TripPayment tripPayment);
    List<TripPayment> getAllTripPayments();
    TripPayment getPaymentById(Long id);
    List<TripPayment> getPaymentByUserId(Long userId);
    List<PaymentMetricDTO> getPaymentMetrics(Long UserId);
    List<UserTripMetricsDTO> getUserPaymentMetrics(Long userId);
}