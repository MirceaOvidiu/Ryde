/** implementation class for the TripService interface
 * @author Dodi Mircea Ovidiu
 * @version 3 Jan 2025
 */

package com.example.ryde.service.implementation;

import com.example.ryde.model.Trip;
import com.example.ryde.service.TripService;
import com.example.ryde.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripServiceImplementation implements TripService {

    private final TripRepository tripRepository;

    @Autowired
    public TripServiceImplementation(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public void saveTrip(Trip trip) {
        tripRepository.save(trip);
    }

    @Override
    public List<Object[]> findAllTrips() {
        return tripRepository.findAllTrips();
    }

    @Override
    public List<Trip> getTripsByCustomer(Long customerId) {
        return tripRepository.findByCustomer(customerId);
    }

    @Override
    public List<Trip> findAll() {
        return tripRepository.findAll();
    }
}
