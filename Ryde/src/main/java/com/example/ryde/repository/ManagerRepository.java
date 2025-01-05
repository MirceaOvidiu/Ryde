package com.example.ryde.repository;

import com.example.ryde.dto.EmployeeByManagerDTO;
import com.example.ryde.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    @Query("SELECT new com.example.ryde.dto.EmployeeByManagerDTO(m.id, m.name, m.surname, e.id, e.name, e.surname) " +
            "FROM Manager m LEFT JOIN Employee e ON m.id = e.manager " +
            "ORDER BY m.id, e.id")
    List<EmployeeByManagerDTO> findEmployeesByManager();
}
