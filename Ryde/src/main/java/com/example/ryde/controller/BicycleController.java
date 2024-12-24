package com.example.ryde.controller;

import com.example.ryde.dto.UserDto;
import com.example.ryde.mapper.UserMapper;
import com.example.ryde.model.Bicycle;
import com.example.ryde.model.MyUser;
import com.example.ryde.service.BicycleService;
import com.example.ryde.service.UserService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
@Controller
public class BicycleController {

    private final BicycleService bicycleService;
    private final UserService userService;

    @Autowired
    public BicycleController(BicycleService bicycleService, UserService userService) {
        this.bicycleService = bicycleService;
        this.userService = userService;
    }

    @GetMapping("/bicycles")
    public String getAllBicycles(Model model) {
        model.addAttribute("bicycles", bicycleService.getAllBicycles());
        return "bicycles";
    }

    @PostMapping("/reserveBicycle")
    public String reserveBicycle(@RequestParam Long bicycleId, Model model) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        attr.getRequest().getSession().setAttribute("reservedBicycleId", bicycleId);
        UserDto userDto = (UserDto) attr.getRequest().getSession().getAttribute("currentUser");
        if (userDto != null) {
            MyUser user = UserMapper.mapToUser(userService.getMyUserById(userDto.getId()));
            Bicycle bicycle = bicycleService.getBicycleById(bicycleId);
            bicycle.setOccupied_by(user.getId());
            bicycleService.reserveBicycle(bicycle, user);
            model.addAttribute("bicycles", bicycleService.getAllBicycles());
            return "redirect:/bicycles";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/reserveBicycle")
    public String showReserveBicyclePage(@RequestParam Long bicycleId, Model model) {
        Bicycle bicycle = bicycleService.getBicycleById(bicycleId);
        model.addAttribute("bicycle", bicycle);
        return "redirect:/bicycles";
    }
}