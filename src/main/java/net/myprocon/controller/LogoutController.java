package net.myprocon.controller;

import ch.qos.logback.classic.Logger;
import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class LogoutController {

    @PostMapping("/logout")
    public String postLogout() {
        // log.info("ログアウト");
        return "redirect:/login";

    }
}
