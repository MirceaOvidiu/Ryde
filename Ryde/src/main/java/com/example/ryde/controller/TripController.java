package com.example.ryde.controller;

import com.example.ryde.dto.UserDto;
import com.example.ryde.model.DockingStation;
import com.example.ryde.model.Trip;
import com.example.ryde.repository.TripRepository;
import com.example.ryde.service.TripService;
import com.example.ryde.service.BicycleService;
import com.example.ryde.service.DockingStationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.Instant;

@Controller
public class TripController {

    private final BicycleService bicycleService;
    private final DockingStationService dockingStationService;
    private final TripService tripService;
    private final TripRepository tripRepository;

    @Autowired
    public TripController(BicycleService bicycleService, DockingStationService dockingStationService, TripService tripService, TripRepository tripRepository) {
        this.bicycleService = bicycleService;
        this.dockingStationService = dockingStationService;
        this.tripService = tripService;
        this.tripRepository = tripRepository;
    }

    @GetMapping("/trip")
    public String getTrip(Model model) {
        model.addAttribute("bicycles", bicycleService.findAvailableBicycles());
        model.addAttribute("dockingStations", dockingStationService.findAllDockingStations());
        return "trip";
    }

    @PostMapping("/startTrip")
    public String startTrip(@RequestParam String bicycleModel,
                            @RequestParam String dockingStation,
                            @RequestParam Instant startTime,
                            HttpSession session) {

        UserDto userDto = (UserDto) session.getAttribute("currentUser");

        if (userDto == null) {
            return "redirect:/login";
        }

        Long customerId = userDto.getId();
        Long bicycleId = bicycleService.getBicycleByModel(bicycleModel).getId();
        DockingStation startLocation = dockingStationService.getDockingStationByName(dockingStation);

        Trip trip = new Trip();
        trip.setCustomer(customerId);
        trip.setBicycle(bicycleId);
        trip.setStart_time(startTime);
        trip.setStart_location(startLocation.getName());

        tripService.saveTrip(trip);

        session.setAttribute("currentTrip", trip);

        return "redirect:/trip";
    }

    @PostMapping("/endTrip")
    public String endTrip(@RequestParam String endLocation, HttpSession session) {

        Trip trip = (Trip) session.getAttribute("currentTrip");
        if (trip == null) {
            return "redirect:/trip";
        }

        DockingStation endDockingStation = dockingStationService.getDockingStationByName(endLocation);

        trip.setEnd_time(Instant.now());
        trip.setEnd_location(endDockingStation.getName());

        tripService.saveTrip(trip);

        session.removeAttribute("currentTrip");

        return "redirect:/userActions";
    }
}
