package net.myprocon.controller;

import java.util.Locale;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import net.myprocon.domain.user.model.MUser;
import net.myprocon.form.GroupOrder;
import net.myprocon.form.SignupForm;
import net.myprocon.domain.user.service.UserApplicationService;
import net.myprocon.domain.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    }
}