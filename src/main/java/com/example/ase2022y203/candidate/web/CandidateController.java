package com.example.ase2022y203.candidate.web;

import com.example.ase2022y203.candidate.domain.Candidate;
import com.example.ase2022y203.candidate.service.messages.CandidateListRequest;
import com.example.ase2022y203.candidate.service.messages.CandidateListResponse;
import com.example.ase2022y203.candidate.web.forms.RegistersForm;
import com.example.ase2022y203.candidate.service.*;
import com.example.ase2022y203.candidate.service.CandidateService;
import com.example.ase2022y203.candidatePersonal.service.CandidatePersonalDTO;
import com.example.ase2022y203.candidatePersonal.service.CandidatePersonalService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
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

    @GetMapping("candidate-profile")
    public ModelAndView getCandidate(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipleEmail  = authentication.getName();

        Optional<CandidateDTO> candidate = candidateService.getCandidateByEmail(currentPrincipleEmail);
        Optional<CandidatePersonalDTO> candidatePersonal = candidatePersonalService
                .getCandidatePersonalByCID(candidate.get().getId());

        if (candidate.isPresent()) {
            model.addAttribute("candidate", candidate.get());
            model.addAttribute("candidatePersonal", candidatePersonal.get());
            var mv = new ModelAndView("candidate/candidate-profile", model.asMap());
            return mv;
        } else {
            return new ModelAndView("redirect:/404");
        }
    }
    @GetMapping("all-candidates")
    public ModelAndView getAllCandidates(Model model) {
        CandidateListRequest candidateListRequest = CandidateListRequest
                .of()
                .build();
        CandidateListResponse candidateListResponse = candidateService.getAllCandidates(candidateListRequest);
        System.out.println(candidateListResponse);
        model.addAttribute("candidate", candidateListResponse.getAllCandidates());
        var mv = new ModelAndView("candidate/all-candidates", model.asMap());
        return mv;
    }
    @GetMapping("add")
    public ModelAndView getNewRegisters(Model model) {
        model.addAttribute("RegistersForm", new RegistersForm());
        var mv = new ModelAndView("registration/registrationForm", model.asMap());
        return mv;
    }
    @PostMapping("save")
    public ModelAndView postNewRegisters(@Valid @ModelAttribute("RegistersForm") RegistersForm register, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            model.addAttribute("RegistersForm", register);
            return new ModelAndView("registration/registrationForm", model.asMap());
        } else {
            BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

            CandidateDTOReg candidate = new CandidateDTOReg(register.getFirst_name(), register.getSurname(),
                    register.getEmail(), bCryptPasswordEncoder.encode(register.getPassword()), register.getCompany_name());
            try{
                candidateService.addNewCandidate(candidate);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                model.addAttribute("RegistersForm", register);
                return new ModelAndView("registration/registrationForm", model.asMap());
            }

            var mv = new ModelAndView("redirect:/successPage");
            return mv;
        }
    }
}
