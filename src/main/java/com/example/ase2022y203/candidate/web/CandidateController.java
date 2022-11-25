package com.example.ase2022y203.candidate.web;


import com.example.ase2022y203.candidate.domain.Register;
import com.example.ase2022y203.candidate.service.*;
import com.example.ase2022y203.candidate.service.CandidateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("candidate")
public class CandidateController {

    private final CandidateService candidateService;

    public CandidateController(CandidateService svc) {
        this.candidateService = svc;
    }

    @GetMapping("candidate-profile/{id}")
    public ModelAndView getCandidate(@PathVariable Integer id, Model model) {

        Optional<CandidateDTO> candidate = candidateService.getCandidateByID(id);

        if (candidate.isPresent()) {
            model.addAttribute("candidate", candidate.get());
            var mv = new ModelAndView("candidate/candidate-profile", model.asMap());
            return mv;
        } else {
            return new ModelAndView("redirect:/404");
        }

    }

    @GetMapping("add")
    public ModelAndView getNewRegisters(Model model) {
        model.addAttribute("Register", new Register());
        var mv = new ModelAndView("registration/registrationForm", model.asMap());
        return mv;

    }

    @PostMapping("add")
    public ModelAndView postRegisters(@Valid Register register, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            return new ModelAndView("registration/registrationForm", model.asMap());
        } else {
            CandidateDTO candidateDTO = new CandidateDTO(register.getID(), register.getFirst_Name(), register.getSurname(),
                    register.getEmail(), register.getPassword(), register.getCompany_Name());
            candidateService.addNewCandidate(candidateDTO);
            var mv = new ModelAndView("redirect:/successPage");
            return mv;

        }
    }
}