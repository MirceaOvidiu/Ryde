package com.example.ryde.service.implementation;

import com.example.ryde.model.Employee;
import com.example.ryde.repository.EmployeeRepository;
import com.example.ryde.service.EmployeeService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeServiceImplementation implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImplementation(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Object[]> findEmployeesWithManagerNames() {
        return employeeRepository.findEmployeesWithManagerNames();
    }
}