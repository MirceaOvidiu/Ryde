/** Controller class for operations that might be performed in the user panel.
 * @author Dodi Mircea Ovidiu
 * @version 30 nov 2024
 */

package com.example.ryde.controller;

import com.example.ryde.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
public class UserActionsController {

    public UserActionsController() {}

    @GetMapping("/userActions")
    public String showUserActionsPage(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "userPanel";
    }

    @GetMapping("/logout")
    public String logoutUser() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        attr.getRequest().getSession().removeAttribute("currentUser");
        return "index";
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
