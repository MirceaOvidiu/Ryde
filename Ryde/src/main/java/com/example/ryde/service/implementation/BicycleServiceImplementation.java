package com.example.ryde.service.implementation;

import com.example.ryde.model.Bicycle;
import com.example.ryde.repository.BicycleRepository;
import com.example.ryde.service.BicycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BicycleServiceImplementation implements BicycleService {

    private final BicycleRepository bicycleRepository;

    @Autowired
    public BicycleServiceImplementation(BicycleRepository bicycleRepository) {
        this.bicycleRepository = bicycleRepository;
    }

    @Override
    public Bicycle getBicycleById(Long id) {
         return bicycleRepository.findBicycleById(id);
    }

    public void reserveBicycle(Bicycle bicycle, Long id) {
        bicycle.setOccupied_by(id);
}

    @Override
    public void saveBicycle(Bicycle bicycle) {
        bicycleRepository.save(bicycle);
    }

    @Override
    public List<Bicycle> getAllBicycles() {
        // return bicycleRepository.findAll(); default method
        return bicycleRepository.findAllBicycles(); // using select b from Bicycle b query
    }

    @Override
    public Bicycle getBicycleByModel(String model) {
        return bicycleRepository.findBicycleByModel(model);
    }

    @Override
    public List<Bicycle> findAvailableBicycles() {
        return bicycleRepository.findAvailableBicycles();
    }
}
