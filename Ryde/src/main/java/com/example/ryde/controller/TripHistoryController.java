/** Controller class for trip history-related operations.
 * @author Dodi Mircea Ovidiu
 * @version 4 Jan 2024
 */

package com.example.ryde.controller;

import com.example.ryde.model.Trip;
import com.example.ryde.service.TripService;
import com.example.ryde.dto.UserDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TripHistoryController {

    private final TripService tripService;

    @Autowired
    public TripHistoryController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping("/tripHistory")
    public String getTripHistory(Model model, HttpSession session) {
        UserDto user = (UserDto) session.getAttribute("currentUser");
        Long customerId = user.getId();

        List<Trip> trips = tripService.getTripsByCustomer(customerId);
        model.addAttribute("trips", trips);
        return "tripHistory";
    }
}