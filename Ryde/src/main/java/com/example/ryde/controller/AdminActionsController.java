package com.example.ryde.controller;

import com.example.ryde.dto.UserDto;
import com.example.ryde.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class AdminActionsController {

    private final UserService userService;

    public AdminActionsController(UserService userService) {
        this.userService = userService;
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
}
