package com.example.ase2022y203.candidate.web;


import com.example.ase2022y203.candidate.service.CandidateService;
import com.example.ase2022y203.candidate.web.forms.CandidateForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("registration/candidate")
public class RegistrationController {
    private final CandidateService candidateService;

    public RegistrationController(CandidateService svc) {
        this.candidateService = svc;
    }

    @GetMapping("add")
    public ModelAndView getNewCandidateForm(Model model) {
        model.addAttribute("CandidateForm", new CandidateForm());
        var mv = new ModelAndView("templates/registration", model.asMap());
        return mv;
    }

    @GetMapping("update")
    public ModelAndView getUpdateCandidateView(Model model) {
        var mv = new ModelAndView("templates/update-registration", model.asMap());
        return mv;
    }
}
