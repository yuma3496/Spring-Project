package net.myfarm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    // Redirect to Admin only page
    @GetMapping("/admin")
    public String getAdmin() {
        return "admin/admin";
    }
}
