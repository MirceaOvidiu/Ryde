/** Controller class for payment-related operations.
 * @author Dodi Mircea Ovidiu
 * @version 5 Jan 2024
 */

package com.example.ryde.controller;

import com.example.ryde.dto.UserDto;
import com.example.ryde.model.TripPayment;
import com.example.ryde.service.TripPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
public class TripPaymentController {

    private final TripPaymentService tripPaymentService;

    @Autowired
    public TripPaymentController(TripPaymentService tripPaymentService) {
        this.tripPaymentService = tripPaymentService;
    }

    @GetMapping("/paymentHistory")
    public String getPaymentsByUser(Model model, HttpSession session) {
        UserDto userDto = (UserDto) session.getAttribute("currentUser");

        Long userId = userDto != null ? userDto.getId() : null;

        if (userId == null) {
            return "redirect:/login";
        }

        List<TripPayment> tripPayments = tripPaymentService.getPaymentByUserId(userId);
        // Round the amount to 2 decimal places
        tripPayments.forEach(payment -> payment.setAmount(Math.round(payment.getAmount() * 100.0) / 100.0));
        model.addAttribute("tripPayments", tripPayments);
        return "paymentHistory";
    }

    @PostMapping("/pay")
    public String payTrip(@RequestParam Long paymentId, HttpSession session) {
        UserDto userDto = (UserDto) session.getAttribute("currentUser");

        if (userDto == null) {
            return "redirect:/login";
        }

        // add the remaining payment details upon payment
        TripPayment tripPayment = tripPaymentService.getPaymentById(paymentId);
        tripPayment.setIban(userDto.getIban());
        tripPayment.setPaid(true);
        tripPaymentService.saveTripPayment(tripPayment);

        return "redirect:/paymentHistory";
    }
}