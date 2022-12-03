package com.example.ase2022y203.vettingOfficers.web;


import com.example.ase2022y203.vettingOfficers.service.VettingOfficersDTO;
import com.example.ase2022y203.vettingOfficers.service.VettingOfficersService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("officer")
public class VettingOfficerController {

    private final VettingOfficersService vettingOfficersService;

    public VettingOfficerController(VettingOfficersService vos) {
        this.vettingOfficersService = vos;
    }

    @GetMapping("officer-profile")
    public ModelAndView getVettingOfficer(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipleEmail = authentication.getName();

        Optional<VettingOfficersDTO> vettingOfficer =  vettingOfficersService.getVettingOfficerByEmail(currentPrincipleEmail);

        if(vettingOfficer.isPresent()){
            model.addAttribute("officer", vettingOfficer.get());
            var mv = new ModelAndView("officer/officer-profile", model.asMap());
            return mv;
        } else {
            return new ModelAndView("redirect:/404");
        }
    }

}
