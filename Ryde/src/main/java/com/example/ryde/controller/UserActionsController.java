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
public class UserActionsController {

    @Autowired
    private UserService userService;

    @GetMapping("/userActions")
    public String showUserActionsPage(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "userActions";
    }


}
