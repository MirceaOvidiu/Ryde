/** interface class for interacting with the Trip entity
 * @author Dodi Mircea Ovidiu
 * @version 3 Jan 2025
 */

package com.example.ryde.service;

import com.example.ryde.model.Trip;
import java.util.List;

public interface TripService {
    void saveTrip(Trip trip);
    List<Object[]> findAllTrips();
    List<Trip> getTripsByCustomer(Long customerId);
    List<Trip> findAll();
}
