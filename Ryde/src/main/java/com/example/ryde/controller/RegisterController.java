/** Controller class for user registration.
 * @author Dodi Mircea Ovidiu
 * @version 15 Nov 2024
 */

package com.example.ryde.controller;

import com.example.ryde.dto.UserDto;
import com.example.ryde.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;

    @GetMapping
    public String register() {
        return "register";
    }

    @PostMapping
    public String createUser(@RequestBody UserDto userDto, Model model) {
        UserDto savedUser = userService.createUser(userDto);
        model.addAttribute("successMessage", "User registered successfully!");
        return "redirect:/";
    }
}