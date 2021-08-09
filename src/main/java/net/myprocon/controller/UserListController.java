package net.myprocon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserListController {

    // User list
    @GetMapping("/list")
    public String getUserList() {

        // Show user list
        return "user/list";
    }

}
