package com.example.ase2022y203.masterAdmin.web;

import com.example.ase2022y203.masterAdmin.service.MasterAdminDTO;
import com.example.ase2022y203.masterAdmin.service.MasterAdminService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("admin")
public class MasterAdminController {

    private final MasterAdminService masterAdminService;

    public MasterAdminController(MasterAdminService masterAdminService) {
        this.masterAdminService = masterAdminService;
    }

    @GetMapping("admin-profile")
    public ModelAndView getMasterAdmin(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipleEmail = authentication.getName();

        Optional<MasterAdminDTO> masterAdmin =  masterAdminService.getMasterAdminByEmail(currentPrincipleEmail);

        if(masterAdmin.isPresent()){
            model.addAttribute("admin", masterAdmin.get());
            var mv = new ModelAndView("admin/admin-profile", model.asMap());
            return mv;
        } else {
            return new ModelAndView("redirect:/404");
        }
    }
}
