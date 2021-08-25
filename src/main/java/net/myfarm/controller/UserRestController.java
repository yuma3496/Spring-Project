package net.myfarm.controller;

import net.myfarm.domain.user.service.UserService;
import net.myfarm.form.UserDetailForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    // Update user
    @PutMapping("/update")
    public int updateUser(UserDetailForm form) {
        // Update user
        userService.updateUserOne(form.getUserId()),
            form.getPassword(),
            form.getUserName();

        return ();
    }

    // DeleteUser
    @DeleteMapping("/delete")
    public int deleteUser(UserDetailForm form) {
        //Delete user
        userService.deleteUserOne((form.getUserId()));

    return ();
    }
}
