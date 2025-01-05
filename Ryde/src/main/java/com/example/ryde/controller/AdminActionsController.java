/** the class for the admin actions controller
 * @author Dodi Mircea Ovidiu
 * @version 4 Jan 2024
 */

package com.example.ryde.controller;

import com.example.ryde.dto.EmployeeByManagerDTO;
import com.example.ryde.dto.UserDto;
import com.example.ryde.dto.PaymentMetricDTO;
import com.example.ryde.dto.UserTripMetricsDTO;
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

    @GetMapping("/adminPanel")
    public String showAdminPanel(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "adminPanel";
    }

    @GetMapping("/users")
    public String showUsersPage(Model model) {
        List<UserDto> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/seeTrips")
    public String showTripsPage(Model model) {
        List<Trip> trips = tripService.findAllTrips();
        model.addAttribute("trips", trips);
        return "tripHistory";
    }

    @GetMapping("/seePayments")
    public String showPaymentsPage(Model model) {
        List<TripPayment> payments = paymentService.getAllTripPayments();
        payments.forEach(payment -> payment.setAmount(Math.round(payment.getAmount() * 100.0) / 100.0));
        model.addAttribute("tripPayments", payments);
        return "paymentHistory";
    }

    @GetMapping("/paymentMetrics")
    public String showPaymentMetricsPage(@RequestParam(required = false) Long userId, Model model) {
        List<UserDto> users = userService.getAllUsers();
        model.addAttribute("users", users);

        if (userId != null) {
            List<PaymentMetricDTO> paymentMetrics = paymentService.getPaymentMetrics(userId);
            List<UserTripMetricsDTO> userTripMetrics = paymentService.getUserPaymentMetrics(userId);

            model.addAttribute("userTripMetrics", userTripMetrics);
            model.addAttribute("paymentMetrics", paymentMetrics);
            model.addAttribute("userId", userId);
        }

        List<Object[]> usersWithMostUnpaidTrips = paymentService.findUserWithMostUnpaidTrips();
        model.addAttribute("usersWithMostUnpaidTrips", usersWithMostUnpaidTrips);

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

}
