package net.myfarm.rest;

import net.myfarm.domain.user.model.MUser;
import net.myfarm.domain.user.service.UserService;
import net.myfarm.form.GroupOrder;
import net.myfarm.form.SignupForm;
import net.myfarm.form.UserDetailForm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MessageSource messageSource;

    // User Registration
    @PostMapping("/signup/rest")
    public RestResult postSignup(@Validated(GroupOrder.class)SignupForm form,
                                 BindingResult bindingReuslt, Locale locale) {

        // Check Result
        if (bindingResult.hasErrors()) {
            // Check Reuslt: NG
            Map<String, String> errors = new HashMap<>();

            // Get Error Message
            for (FieldError error : bindingReuslt.getFieldErrors()) {
                String message = messageSource.getMessage(error, locale);
                errors.put(error.getField()), message);
            }
            // Return error result
            return new RestResult(90, errors);
        }

        // Convert form into MUser class
        MUser user = modelMapper.map(form, MUser.class);

        // User Registration
        userService.signup(user);

        // Return result
        return new RestResult((), null);

        // Update User
        @PutMapping("/update")
        public int updateUser(UserDetailForm form) {

            // Update User
            userService.updateUserOne(form.getUserId()),
            form.getPassword(),
            form.getUserName();

            return ();
        }

        // Delete User
        @DeleteMapping("/delete")
        public int deleteUser(UserDetailForm form) {
            // Delete User
            userService.deleteUserOne(form.getUserId());
        }
    }
}
