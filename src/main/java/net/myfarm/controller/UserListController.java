package net.myfarm.controller;

import net.myfarm.domain.user.model.MUser;
import net.myfarm.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserListController {

    @Autowired
    private UserService userService;

    // User list
    @GetMapping("/list")
    public String getUserList(Model model) {

        // Get User List
        List<MUser> userList = userService.getUsers();

        // Add Model
        model.addAttribute("userList", userList);

        // Show user list
        return "user/list";
    }
}