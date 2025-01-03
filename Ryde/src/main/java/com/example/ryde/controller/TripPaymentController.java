package com.example.ryde.controller;

import com.example.ryde.model.TripPayment;
import com.example.ryde.service.TripPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@Controller
@RequestMapping("/tripPayments")
public class TripPaymentController {

    private final TripPaymentService tripPaymentService;

    @Autowired
    public TripPaymentController(TripPaymentService tripPaymentService) {
        this.tripPaymentService = tripPaymentService;
    }

    @GetMapping
    public String getAllTripPayments(Model model) {
        List<TripPayment> tripPayments = tripPaymentService.getAllTripPayments();
        model.addAttribute("tripPayments", tripPayments);
        return "tripPayments";
    }

    @GetMapping("/{id}")
    public String getTripPaymentById(@PathVariable Long id, Model model) {
        TripPayment tripPayment = tripPaymentService.getTripPaymentById(id);
        model.addAttribute("tripPayment", tripPayment);
        return "tripPaymentDetails";
    }

    @PostMapping
    public String createTripPayment(@RequestParam Long tripId,
                                    @RequestParam Instant paymentDate,
                                    @RequestParam Double amount,
                                    @RequestParam String iban) {
        TripPayment tripPayment = new TripPayment();
        tripPayment.setTrip_id(tripId);
        tripPayment.setPayment_date(paymentDate);
        tripPayment.setAmount(amount);
        tripPayment.setIban(iban);
        tripPaymentService.saveTripPayment(tripPayment);
        return "redirect:/tripPayments";
    }
}