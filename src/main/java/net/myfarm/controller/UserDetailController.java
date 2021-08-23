package net.myfarm.controller;

import lombok.extern.slf4j.Slf4j;
import net.myfarm.domain.user.model.MUser;
import net.myfarm.domain.user.service.UserService;
import net.myfarm.form.UserDetailForm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserDetailController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    /* Get User Detail Page */
    @GetMapping("/detail/{userId:.+}")
    public String getUser(UserDetailForm form, Model model,
                          @PathVariable("userId") String userId) {

        // Get One User
        MUser user = userService.getUserOne(userId);
        user.setPassword(null);

        // Convert MUser into form
        form = modelMapper.map(user, UserDetailForm.class);
        form.setSalaryList(user.getSalaryList());

        // Register model
        model.addAttribute("userDetailForm", form);

        // User Detail Page
        return "user/detail";
    }

    /* Update User */
    @PostMapping(value = "/detail", params = "update")
    public String updateUser(UserDetailForm form, Model model) {

        try {
            // Update User
            userService.updateUserOne(form.getUserId(),
                    form.getPassword(),
                    form.getUserName());
        } catch (Exception e) {
            log.error("User update error", e);
        }

        // Redirect to User List Page
        return "redirect:/user/list";
    }

    /* Delete User */
    @PostMapping(value = "/detail", params = "delete")
    public String deleteUser(UserDetailForm form, Model model) {

        // Delete User
        userService.deleteUserOne((form.getUserId()));

        // Redirect to User List
        return "redirect:/user/list";
        }
    }
