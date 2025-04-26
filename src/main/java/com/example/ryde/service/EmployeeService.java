/** service interface for Employee entity
 * @author Dodi Mircea Ovidiu
 * @version 6 jan 2025
 */

package com.example.ryde.service;

import com.example.ryde.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    void saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    List<Object[]> findEmployeesWithManagerNames();
}
