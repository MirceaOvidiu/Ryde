/** the class for the admin actions controller
 * @author Dodi Mircea Ovidiu
 * @version 4 Jan 2024
 */

package com.example.ryde.controller;

import com.example.ryde.dto.*;
import com.example.ryde.model.Employee;
import com.example.ryde.model.Manager;
import com.example.ryde.model.Trip;
import com.example.ryde.model.TripPayment;
import com.example.ryde.service.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminActionsController {

    // could use Autowiring, but constructor injection is recommended security-wise

    private final UserService userService;
    private final TripService tripService;
    private final TripPaymentService paymentService;
    private final ManagerService managerService;
    private final EmployeeService employeeService;


    public AdminActionsController(UserService userService, TripService tripService, ManagerService managerService,
                                  EmployeeService employeeService,
                                  @Qualifier("tripPaymentServiceImplementation")TripPaymentService paymentService) {
        this.tripService = tripService;
        this.userService = userService;
        this.paymentService = paymentService;
        this.managerService = managerService;
        this.employeeService = employeeService;
    }

    // Only get mapping is used, as the admin panel is only for viewing
    @GetMapping("/adminPanel")
    public String showAdminPanel(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "adminPanel";
    }

    // Same for the /users route
    @GetMapping("/users")
    public String showUsersPage(Model model) {
        List<UserDto> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/seeTrips")
    public String showTripsPage(Model model) {
        List<Trip> trips = tripService.findAll();
        model.addAttribute("trips", trips);
        return "tripHistory";
    }

    @GetMapping("/tripLocations")
    public String showTripLocationsPage(Model model) {
        List<Object[]> tripLocations = tripService.findAllTrips();
        model.addAttribute("tripLocations", tripLocations);
        return "tripLocations";
    }

    @GetMapping("/seePayments")
    public String showPaymentsPage(Model model) {
        List<TripPayment> payments = paymentService.getAllTripPayments();
        // round the amount to 2 decimal places
        payments.forEach(payment -> payment.setAmount(Math.round(payment.getAmount() * 100.0) / 100.0));
        model.addAttribute("tripPayments", payments);
        return "paymentHistory";
    }

    @GetMapping("/paymentMetrics")
    public String showPaymentMetricsPage(@RequestParam(required = false) Long userId, Model model) {
        List<UserDto> users = userService.getAllUsers();
        model.addAttribute("users", users);

        // only if a user is selected
        if (userId != null) {
            List<PaymentMetricDTO> paymentMetrics = paymentService.getPaymentMetrics(userId);
            List<UserTripMetricsDTO> userTripMetrics = paymentService.getUserPaymentMetrics(userId);

            model.addAttribute("userTripMetrics", userTripMetrics);
            model.addAttribute("paymentMetrics", paymentMetrics);
            model.addAttribute("userId", userId);
        }

        // work regardless of user id
        List<Object[]> usersWithMostSpent = paymentService.findUserSpendingOnTrips();
        List<Object[]> usersWithMostUnpaidTrips = paymentService.findUserWithMostUnpaidTrips();
        List<String> usernamesForUnpaidTrips = paymentService.findUsernamesForUnpaidTrips();

        model.addAttribute("usersWithMostUnpaidTrips", usersWithMostUnpaidTrips);
        model.addAttribute("userSpendingOnTrips", usersWithMostSpent);
        model.addAttribute("usernamesForUnpaidTrips", usernamesForUnpaidTrips);

        return "paymentMetrics";
    }

    @PostMapping("/deleteUser/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return "redirect:/users";
    }

    @GetMapping("/managers")
    public String showManagersPage(Model model) {
        List<Manager> managers = managerService.getAllManagers();
        List<EmployeeByManagerDTO> employeesByManager = managerService.getEmployeesByManager();
        model.addAttribute("employeesByManager", employeesByManager);
        model.addAttribute("managers", managers);
        model.addAttribute("manager", new Manager());
        return "managers";
    }

    @PostMapping("/managers/save")
    public String saveManager(@ModelAttribute("manager") Manager manager) {
        managerService.saveManager(manager);
        return "redirect:/managers";
    }

    @PostMapping("/managers/delete/{id}")
    public String deleteManager(@PathVariable Long id) {
        managerService.deleteManager(id);
        return "redirect:/managers";
    }

    @GetMapping("/employees")
    public String showEmployeesPage(Model model) {
        model.addAttribute("employee", new Employee());
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);

        List<Object[]> employeesWithManagerNames = employeeService.findEmployeesWithManagerNames();
        model.addAttribute("employeesWithManagerNames", employeesWithManagerNames);

        List<Manager> managers = managerService.getAllManagers();
        model.addAttribute("managers", managers);
        return "employee";
    }

    @GetMapping("/employees/create")
    public String showCreateEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        List<Manager> managers = managerService.getAllManagers();
        model.addAttribute("managers", managers);
        return "employee";
    }

    @PostMapping("/employees/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/payrollMetrics")
    public String getPayrollMetrics(Model model) {
        model.addAttribute("departments", managerService.DepartmentsByAvgSalary());
        model.addAttribute("employees", managerService.getEmployeesWithLowerSalaryThanITDepartment());
        model.addAttribute("employees2", managerService.getEmployeesWithHigherSalaryThanHRDepartment());
        return "payrollMetrics";
    }

    @GetMapping("/departmentMetrics")
    public String getDepartmentMetrics(Model model) {
        model.addAttribute("departments", managerService.sortDepartmentsByNrOfEmployees());
        return "departmentMetrics";
    }
}
