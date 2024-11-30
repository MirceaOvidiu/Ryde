package com.example.ryde.controller;

import com.example.ryde.service.BicycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BicycleController {

    private final BicycleService bicycleService;

    @Autowired
    public BicycleController(BicycleService bicycleService) {
        this.bicycleService = bicycleService;
    }

    @GetMapping("/bicycles")
    public String getAllBicycles(Model model) {
        model.addAttribute("bicycles", bicycleService.getAllBicycles());
        return "bicycles";
    }
}