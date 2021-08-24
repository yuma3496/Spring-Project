package net.myfarm.controller;

import java.util.Locale;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import net.myfarm.domain.user.model.MUser;
import net.myfarm.form.GroupOrder;
import net.myfarm.form.SignupForm;
import net.myfarm.domain.user.service.UserApplicationService;
import net.myfarm.domain.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@Slf4j
public class SignupController {

    @Autowired
    private UserApplicationService userApplicationService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    // User Registration page
    @GetMapping("/signup")
    public String getSignup(Model model, Locale locale,
                            @ModelAttribute SignupForm form) {

        // Retrieve Gender
        Map<String, Integer> genderMap  = userApplicationService.getGenderMap(locale);
        model.addAttribute("genderMap", genderMap);

        // Redirect to signup page
        return "user/signup";
    }

    @PostMapping("/signup")
    public String postSignup(Model model, Locale locale,

        @ModelAttribute @Validated(GroupOrder.class) SignupForm form,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            // Back to signup page
            return getSignup(model, locale, form);
        }

        log.info(form.toString());
        // Redirect to login page

        //Convert form into MUser class
        MUser user = modelMapper.map(form, MUser.class);

        //User Registration
        userService.signup(user);

        return "redirect:/login";

    // Exception handling for databases
    @ExceptionHandler(DataAccessException.class)
    public String dataAccessExceptionHandler(DataAccessException e, Model model){

            // Set empty string
            model.addAttribute("error", "");

            // Add model to message
            model.addAttribute("message", "Exception Error in SignupController");

            // HTTP error code (500) to model
            model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

            return "error";
        }

    // Other exception handling
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e, Model model) {

            // Set empty string
            model.addAttribute("error", "");

            // Add messages to Model
            model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

            return "error";
        }
    }
}