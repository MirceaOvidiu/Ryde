package com.example.ryde.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ryde.model.Bicycle;

public interface BicycleRepository extends JpaRepository<Bicycle, Long> {
}
