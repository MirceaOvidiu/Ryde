package com.example.ryde.service;

import com.example.ryde.model.Bicycle;
import java.util.List;

public interface BicycleService {
    List<Bicycle> getAllBicycles();
    Bicycle getBicycleById(Long id);
    void reserveBicycle(Bicycle bicycle, Long id);
    void saveBicycle(Bicycle bicycle);
    Bicycle getBicycleByModel(String model);
    List<Bicycle> findAvailableBicycles();
}
