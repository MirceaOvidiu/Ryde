package com.example.ryde.service.implementation;

import com.example.ryde.dto.EmployeeByManagerDTO;
import com.example.ryde.model.Manager;
import com.example.ryde.model.Employee;
import com.example.ryde.service.ManagerService;
import com.example.ryde.repository.ManagerRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImplementation implements ManagerService {
    private final ManagerRepository managerRepository;
    private final EntityManager entityManager;

    public ManagerServiceImplementation(ManagerRepository managerRepository, EntityManager entityManager) {
        this.managerRepository = managerRepository;
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void saveManager(Manager manager) {
        String sql = "INSERT INTO management_member (name, surname, department,salary) " +
                "VALUES (:name, :surname, :department, :salary)";
        entityManager.createNativeQuery(sql)
                .setParameter("name", manager.getName())
                .setParameter("surname", manager.getSurname())
                .setParameter("department", manager.getDepartment())
                .setParameter("salary", manager.getSalary())
                .executeUpdate();
    }

    @Override
    @Transactional
    public void deleteManager(Long id) {
        String sql = "DELETE FROM management_member WHERE id = :id";
        entityManager.createNativeQuery(sql)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public List<Manager> getAllManagers() {
        return managerRepository.findAll();
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
