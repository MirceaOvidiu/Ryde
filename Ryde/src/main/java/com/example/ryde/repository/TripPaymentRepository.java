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

    // Query to get the total amount of money spent by each user, along with their email
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
            "HAVING COUNT(tp) != 0 " +
            "ORDER BY unpaidTripCount DESC")
    List<Object[]> findUserWithMostUnpaidTrips();

    /// complex query 2 to get the users with the highest debt
    @Query("SELECT u, (SELECT SUM(tp.amount) FROM TripPayment tp WHERE tp.userId = u.id AND tp.paid = false) as totalUnpaidSpent " +
            "FROM MyUser u " +
           //  "WHERE (SELECT SUM(tp.amount) FROM TripPayment tp WHERE tp.userId = u.id AND tp.paid = false) IS NOT NULL " +
            "ORDER BY (SELECT SUM(tp.amount) FROM TripPayment tp WHERE tp.userId = u.id AND tp.paid = false) LIMIT 3")
    List<Object[]> findUserSpendingOnTrips();


    ///  complex query 1 to get the usernames of the users who have unpaid trips
    @Query(value = "SELECT DISTINCT u.username " +
            "FROM \"user\" u " +
            "WHERE u.id IN " +
            "(SELECT tp.user_id " +
            "FROM trip_payment tp " +
            "WHERE tp.trip_id IN (SELECT t.id FROM trip t WHERE tp.paid = false))", nativeQuery = true)
    List<String> findUsernamesForUnpaidTrips();
}