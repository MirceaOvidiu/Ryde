package com.example.ryde.service.implementation;

import com.example.ryde.dto.EmployeeByManagerDTO;
import com.example.ryde.model.Manager;
import com.example.ryde.model.Employee;
import com.example.ryde.service.ManagerService;
import com.example.ryde.repository.ManagerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImplementation implements ManagerService {
    private final ManagerRepository managerRepository;

    public ManagerServiceImplementation(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public void saveManager(Manager manager) {
        managerRepository.save(manager);
    }

    @Override
    public List<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    @Override
    public void deleteManager(Long id) {
        managerRepository.deleteById(id);
    }

    @Override
    public List<EmployeeByManagerDTO> getEmployeesByManager() {
        return managerRepository.findEmployeesByManager();
    }

    @Override
    public List<Object[]> DepartmentsByAvgSalary() {
        return managerRepository.findDepartmentsSortedByAverageSalary();
    }

    @Override
    public List<Employee> getEmployeesWithLowerSalaryThanITDepartment() {
        return managerRepository.findEmployeesWithLowerSalaryThanITDepartment();
    }

    @Override
    public List<Employee> getEmployeesWithHigherSalaryThanHRDepartment() {
        return managerRepository.findEmployeesWithHigherSalaryThanHRDepartment();
    }

    @Override
    public List<Object[]> sortDepartmentsByNrOfEmployees() {
        return managerRepository.sortDepartmentsByNrOfEmployees();
    }
}
