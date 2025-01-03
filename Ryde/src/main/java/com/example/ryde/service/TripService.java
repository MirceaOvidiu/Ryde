package com.example.ryde.service;

import com.example.ryde.model.Trip;
import java.util.List;

public interface TripService {
    void saveTrip(Trip trip);
    List<Trip> findAllTrips();
    List<Trip> getTripsByCustomer(Long customerId);
}
