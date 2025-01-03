package com.example.ryde.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ryde.model.Bicycle;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BicycleRepository extends JpaRepository<Bicycle, Long> {

    @Query("SELECT b FROM Bicycle b")
    List<Bicycle> findAllBicycles();

    Bicycle findBicycleById(Long id);

    @Query("SELECT b FROM Bicycle b JOIN FETCH b.location")
    List<Bicycle> findAllBicyclesByLocation();

    Bicycle findBicycleByModel(String model);
}
