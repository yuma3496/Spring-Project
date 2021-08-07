package net.myprocon.myprocon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLogin() {
        return "login/login";
    }

    // Redirect to User List page
    @PostMapping("/login")
    public String postLogin() {
        return "redirect:/user/list";
    }
}