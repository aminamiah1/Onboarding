package com.example.ase2022y203.candidateReferences.web;


import com.example.ase2022y203.candidate.service.CandidateDTO;
import com.example.ase2022y203.candidate.service.CandidateService;
import com.example.ase2022y203.candidatePersonal.service.CandidatePersonalDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/reference")
public class CandidateReferencesController {

    private final CandidateService candidateService;

    public CandidateReferencesController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping("reference-portal")
    public ModelAndView getReferences(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipleEmail  = authentication.getName();

        Optional<CandidateDTO> candidate = candidateService.getCandidateByEmail(currentPrincipleEmail);

        if (candidate.isPresent()) {
            model.addAttribute("candidate", candidate.get());
            var mv = new ModelAndView("references/references-portal", model.asMap());
            return mv;
        } else {
            return new ModelAndView("redirect:/404");
        }

    }


}


