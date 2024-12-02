package com.example.ryde.service;

import com.example.ryde.model.MyUser;
import com.example.ryde.model.Bicycle;
import java.util.List;

public interface BicycleService {
    List<Bicycle> getAllBicycles();
    Bicycle getBicycleById(Long id);
    void reserveBicycle(Bicycle bicycle, MyUser user);
}
