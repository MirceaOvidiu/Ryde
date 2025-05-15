/** Controller class for the welcome page.
 * @author Dodi Mircea Ovidiu
 * @version 10 Nov 2024
 */

package com.example.ryde.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String index() {
        return "index";
    }
}
