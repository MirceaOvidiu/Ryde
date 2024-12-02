package com.example.ryde.controller;

import com.example.ryde.dto.UserDto;
import com.example.ryde.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
public class UserActionsController {

    private final UserService userService;

    public UserActionsController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userActions")
    public String showUserActionsPage(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "userActions";
    }

    @GetMapping("/logout")
    public String logoutUser() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        attr.getRequest().getSession().removeAttribute("currentUser");
        return "redirect:/login";
    }

    @GetMapping("/userDetails")
    public String showUserDetailsPage(Model model){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        UserDto userDto = (UserDto) attr.getRequest().getSession().getAttribute("currentUser");
        if (userDto != null) {
            model.addAttribute("user", userDto);
            return "userProfile";
        } else {
            return "redirect:/index";
        }
    }
}
