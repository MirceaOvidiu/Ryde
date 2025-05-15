package com.example.ryde.service;

import com.example.ryde.dto.EmployeeByManagerDTO;
import com.example.ryde.model.Employee;
import com.example.ryde.model.Manager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ManagerService {
    void saveManager(Manager manager);
    List<Manager> getAllManagers();
    void deleteManager(Long id);
    List<EmployeeByManagerDTO> getEmployeesByManager();
    List<Object[]> DepartmentsByAvgSalary();
    List<Employee> getEmployeesWithLowerSalaryThanITDepartment();
    List<Employee> getEmployeesWithHigherSalaryThanHRDepartment();
    List<Object[]> sortDepartmentsByNrOfEmployees();
}