package com.example.ryde.service.implementation;

import com.example.ryde.model.Bicycle;
import com.example.ryde.repository.BicycleRepository;
import com.example.ryde.service.BicycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BicycleServiceImplementation implements BicycleService {

    private final BicycleRepository bicycleRepository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BicycleServiceImplementation(BicycleRepository bicycleRepository) {
        this.bicycleRepository = bicycleRepository;
        this.jdbcTemplate = new JdbcTemplate();
    }

    @Override
    public Bicycle getBicycleById(Long id) {
       String sql = "SELECT * FROM bicycle WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
              Bicycle b = new Bicycle();
              b.setId(rs.getLong("id"));
              b.setModel(rs.getString("model"));
              b.setHourly_rate(rs.getFloat("price"));
              b.setOccupied_by(rs.getLong("occupied_by"));
              return b;
         }, id);

        // AWJ =))
        // return bicycleRepository.findBicycleById(id);
    }

    @Override
    public List<Bicycle> getAllBicycles() {
        // return bicycleRepository.findAll(); default method
        return bicycleRepository.findAllBicycles(); // using select b from Bicycle b query
    }
}
