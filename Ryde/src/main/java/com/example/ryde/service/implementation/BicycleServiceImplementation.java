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
    public List<Bicycle> getAllBicycles() {
        return bicycleRepository.findAll();
    }
}
