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

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute LoginDto loginDto, Model model) {
        try {
            UserDto userDto = userService.loginUser(loginDto);
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            attr.getRequest().getSession(true).setAttribute("currentUser", userDto);
            model.addAttribute("user", userDto);

            if ("ADMIN".equals(userDto.getRole())) {
                return "redirect:/adminPanel";
            }

            return "redirect:/userActions";
        } catch (RuntimeException e) {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
}