/** the class for the admin actions controller
 * @author Dodi Mircea Ovidiu
 * @version 4 Jan 2024
 */

package com.example.ryde.controller;

import com.example.ryde.dto.UserDto;
import com.example.ryde.dto.PaymentMetricDTO;
import com.example.ryde.dto.UserTripMetricsDTO;
import com.example.ryde.model.Trip;
import com.example.ryde.model.TripPayment;
import com.example.ryde.service.TripService;
import com.example.ryde.service.TripPaymentService;
import com.example.ryde.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class AdminActionsController {

    private final UserService userService;
    private final TripService tripService;
    private final TripPaymentService paymentService;

    public AdminActionsController(UserService userService, TripService tripService,
                                  @Qualifier("tripPaymentServiceImplementation")TripPaymentService paymentService) {
        this.tripService = tripService;
        this.userService = userService;
        this.paymentService = paymentService;
    }

    @GetMapping("/adminPanel")
    public String showAdminPanel(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "adminPanel";
    }

    @GetMapping("/seeUsers")
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

    @GetMapping("/deleteUser/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return "redirect:/users";
    }
}
