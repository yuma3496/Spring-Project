package net.myfarm.controller;

import net.myfarm.domain.user.model.MUser;
import net.myfarm.domain.user.service.UserService;
import net.myfarm.form.UserListForm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserListController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    // User list
    @GetMapping("/list")
    public String getUserList(@ModelAttribute UserListForm form, Model model) {

        // Convert MUser into class
        MUser user = modelMapper.map(form, MUser.class);

        // Get User List
        List<MUser> userList = userService.getUsers(user);

        // Add Model
        model.addAttribute("userList", userList);

        // Show user list
        return "user/list";
    }

    // User Search Function
    @PostMapping("/list")
    public String postUserList(@ModelAttribute UserListForm form, Model model) {

        // Convert MUser into class
        MUser user = modelMapper.map(form, MUser.class);

        // User Search
        List<MUser> userList = userService.getUsers(user);

        // Register Model
        model.addAttribute("userList", userList);

        // Show User List Page
        return "user/list";
    }
}