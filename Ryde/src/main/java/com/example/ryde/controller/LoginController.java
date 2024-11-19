package com.example.ryde.controller;

import com.example.ryde.dto.LoginDto;
import com.example.ryde.dto.UserDto;
import com.example.ryde.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("loginDto", new LoginDto());
        return "login"; // The name of the Thymeleaf template for the login page
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute LoginDto loginDto, Model model) {
        try {
            UserDto userDto = userService.loginUser(loginDto);
            model.addAttribute("user", userDto);
            return "userProfile"; // The name of the Thymeleaf template to display user data
        } catch (RuntimeException e) {
            model.addAttribute("error", "Invalid username or password");
            return "login"; // The name of the Thymeleaf template for the login page
        }
    }
}