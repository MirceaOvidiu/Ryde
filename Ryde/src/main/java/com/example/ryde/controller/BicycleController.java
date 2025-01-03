package com.example.ryde.controller;

import com.example.ryde.dto.UserDto;
import com.example.ryde.model.Bicycle;
import com.example.ryde.service.BicycleService;
import com.example.ryde.service.DockingStationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.sql.DataSource;

@Controller
public class BicycleController {

    private final BicycleService bicycleService;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BicycleController(DockingStationService dockingStationService, BicycleService bicycleService, DataSource dataSource) {
        this.bicycleService = bicycleService;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @GetMapping("/bicycles")
    public String getAllBicycles(Model model) {
        model.addAttribute("bicycles", bicycleService.getAllBicycles());
        return "bicycles";
    }

    @PostMapping("/reserveBicycle")
    public String reserveBicycle(@RequestParam Long bicycleId) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        UserDto userDto = (UserDto) session.getAttribute("currentUser");
        Bicycle bicycle = bicycleService.getBicycleById(bicycleId);

        String updateQuery = "UPDATE bicycle SET occupied_by = ? WHERE id = ?";
        jdbcTemplate.update(updateQuery, userDto.getId(), bicycle.getId());
        session.setAttribute("reservedBicycleId", bicycleId);
        return "redirect:/bicycles";
    }

    @GetMapping("/reserveBicycle")
    public String showReserveBicyclePage(@RequestParam Long bicycleId, Model model) {
        Bicycle bicycle = bicycleService.getBicycleById(bicycleId);
        model.addAttribute("bicycle", bicycle);
        return "redirect:/bicycles";
    }
}