/** TripPaymentRepository interface
 * @author Dodi Mircea Ovidiu
 * @version 3 Jan 2025
 */

package com.example.ryde.repository;

import com.example.ryde.dto.UserTripMetricsDTO;
import com.example.ryde.model.TripPayment;
import com.example.ryde.dto.PaymentMetricDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface TripPaymentRepository extends JpaRepository<TripPayment, Long> {
    List<TripPayment> findByUserId(Long userId);

    /// Query to get the total amount of money spent by each user, along with their email
    @Query("SELECT new com.example.ryde.dto.PaymentMetricDTO(tp.userId, SUM(tp.amount), u.email) " +
            "FROM TripPayment tp JOIN MyUser u ON tp.userId = u.id " +
            "WHERE tp.userId = :userId " +
            "GROUP BY tp.userId, u.email")
    List<PaymentMetricDTO> findPaymentMetrics(@Param("userId") Long userId);

    @Query("SELECT new com.example.ryde.dto.UserTripMetricsDTO(tp.userId, " +
            "CAST((SELECT COUNT(t) FROM Trip t WHERE t.customer = tp.userId) AS integer), " +
            "CAST(SUM(CASE WHEN tp.paid = TRUE THEN 1 ELSE 0 END) AS integer), " +
            "CAST(SUM(CASE WHEN tp.paid = FALSE THEN 1 ELSE 0 END) AS integer)) " +
            "FROM TripPayment tp " +
            "WHERE tp.userId = :userId " +
            "GROUP BY tp.userId")
    List<UserTripMetricsDTO> findUserTripMetrics(Long userId);

    @Query("SELECT u, COUNT(tp) as unpaidTripCount " +
            "FROM MyUser u JOIN TripPayment tp ON u.id = tp.userId " +
            "WHERE tp.paid = false " +
            "GROUP BY u.id " +
            "ORDER BY unpaidTripCount DESC")
    List<Object[]> findUserWithMostUnpaidTrips();

    @Query("SELECT u, SUM(tp.amount) as totalSpent " +
            "FROM MyUser u JOIN TripPayment tp ON u.id = tp.userId " +
            "GROUP BY u.id " +
            "ORDER BY totalSpent DESC")
    List<Object[]> findUserSpendingOnTrips();

    @Query(value = "SELECT DISTINCT u.username " +
            "FROM trip_payment tp " +
            "JOIN \"user\" u ON tp.user_id = u.id " +
            "WHERE tp.trip_id IN (SELECT t.id FROM trip t WHERE tp.paid = false)", nativeQuery = true)
    List<String> findUsernamesForUnpaidTrips();
}