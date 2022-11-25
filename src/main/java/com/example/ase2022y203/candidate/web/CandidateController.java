package com.example.ase2022y203.candidate.web;


import com.example.ase2022y203.candidate.service.CandidateDTO;
import com.example.ase2022y203.candidate.service.CandidateService;
import com.example.ase2022y203.candidatePersonal.service.CandidatePersonalDTO;
import com.example.ase2022y203.candidatePersonal.service.CandidatePersonalService;
import com.example.ase2022y203.candidatePersonal.service.messages.SingleCandidatePersonalRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("candidate")
public class CandidateController {

    private final CandidateService candidateService;
    private final CandidatePersonalService candidatePersonalService;

    public CandidateController(CandidateService svc, CandidatePersonalService cps){
        this.candidateService = svc;
        this.candidatePersonalService = cps;
    }

    @GetMapping("candidate-profile/{id}")
    public ModelAndView getCandidate(@PathVariable Integer id, Model model){

        Optional<CandidateDTO> candidate = candidateService.getCandidateByID(id);
        Optional<CandidatePersonalDTO> candidatePersonal = candidatePersonalService.getCandidatePersonalByCID(id);

        if(candidate.isPresent()){
            model.addAttribute("candidate", candidate.get());
            model.addAttribute("candidatePersonal", candidatePersonal.get());
            var mv = new ModelAndView("candidate/candidate-profile", model.asMap());
            return mv;
        } else {
            return new ModelAndView("redirect:/404");
        }

    }

}
