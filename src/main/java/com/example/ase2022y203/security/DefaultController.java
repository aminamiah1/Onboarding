package com.example.ase2022y203.security;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DefaultController {
    @RequestMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/admin/";
        } else if (request.isUserInRole("ROLE_USER")) {
            return "redirect:/candidate/candidate-profile";
        } else if (request.isUserInRole("ROLE_OFFICER")) {
            return "redirect:/officer/officer-profile";
        }
        return "redirect:/user/";
    }
}
