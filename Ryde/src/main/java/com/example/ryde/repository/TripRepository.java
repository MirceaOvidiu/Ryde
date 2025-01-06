/** Trip repo interface
 * @author Dodi Mircea Ovidiu
 * @version 2 Jan 2025
 */

package com.example.ryde.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ryde.model.Trip;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    @Query(value = "SELECT t.id AS tripId, t.start_time AS startTime, t.end_time AS endTime, ds1.name AS startStationName, ds1.location AS startStationLocation, ds2.name AS endStationName, ds2.location AS endStationLocation, u.username AS username " +
            "FROM trip t " +
            "JOIN docking_station ds1 ON t.start_location = ds1.name " +
            "JOIN docking_station ds2 ON t.end_location = ds2.name " +
            "JOIN \"user\" u ON t.customer = u.id", nativeQuery = true)
    List<Object[]> findAllTrips();

    List<Trip> findByCustomer(Long customerId);
}