/** Repo class for interacting with the manager model.
 * @author Dodi Mircea Ovidiu
 * @version 6 jan 2025
 */

package com.example.ryde.repository;

import com.example.ryde.dto.EmployeeByManagerDTO;
import com.example.ryde.model.Employee;
import com.example.ryde.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    @Query("SELECT new com.example.ryde.dto.EmployeeByManagerDTO(m.id, m.name, m.surname, e.id, e.name, e.surname) " +
            "FROM Manager m " +
            "LEFT JOIN Employee e ON m.id = e.manager " +
            "ORDER BY m.id, e.id")
    List<EmployeeByManagerDTO> findEmployeesByManager();

    @Query("SELECT d.name, AVG(e.salary) AS avgSalary " +
            "FROM Department d " +
            "JOIN Employee e ON e.department = d.name " +
            "GROUP BY d.name " +
            "ORDER BY avgSalary DESC")
    List<Object[]> findDepartmentsSortedByAverageSalary();

    ///  complex query 3
    @Query("SELECT e " +
            "FROM Employee e " +
            "WHERE e.salary < ALL (SELECT it.salary FROM Employee it WHERE it.department = 'IT')")
    List<Employee> findEmployeesWithLowerSalaryThanITDepartment();

    ///  complex query 4
    @Query("SELECT e " +
            "FROM Employee e " +
            "WHERE e.salary > ALL (SELECT hr.salary FROM Employee hr WHERE hr.department = 'HR')")
    List<Employee> findEmployeesWithHigherSalaryThanHRDepartment();

    ///  complex query 5 to sort the departments by the number of employees
    @Query("SELECT d, (SELECT COUNT(e) FROM Employee e WHERE e.department = d.name) AS employeeCount " +
            "FROM Department d " +
            "ORDER BY (SELECT COUNT(e) FROM Employee e WHERE e.department = d.name) DESC")
    List<Object[]> sortDepartmentsByNrOfEmployees();
}
