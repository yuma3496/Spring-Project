package net.myfarm.controller;

import net.myfarm.domain.user.model.MUser;
import net.myfarm.domain.user.service.UserService;
import net.myfarm.form.UserDetailForm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserDetailController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    // Get User Detail Page
    public String getUser(UserDetailForm form, Model model,
                          @PathVariable("userId") String userId) {

        // Get One User
        MUser user = userService.getUserOne(userId);
        user.setPassword(null);

        // Convert MUser into form
        form = modelMapper.map(user, UserDetailForm.class);

        // Register model
        model.addAttribute("userDetailForm", form);

        // User Detail Page
        return "user/detail";
    }
}
