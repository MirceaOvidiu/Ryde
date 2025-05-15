package com.example.ryde.repository;

import com.example.ryde.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e.id, e.name, e.surname, e.department, e.salary, m.name AS managerName, m.surname AS managerSurname " +
            "FROM Employee e " +
            "LEFT JOIN Manager m ON e.manager = m.id")
    List<Object[]> findEmployeesWithManagerNames();
}
