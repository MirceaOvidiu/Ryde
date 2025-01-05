package com.example.ryde.service;

import com.example.ryde.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    void saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
}
