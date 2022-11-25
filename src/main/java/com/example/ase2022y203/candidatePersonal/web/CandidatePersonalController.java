package com.example.ase2022y203.candidatePersonal.web;

import com.example.ase2022y203.candidate.service.CandidateDTO;
import com.example.ase2022y203.candidate.service.CandidateService;
import com.example.ase2022y203.candidatePersonal.service.CandidatePersonalService;
import com.example.ase2022y203.candidatePersonal.service.messages.SingleCandidatePersonalRequest;
import com.example.ase2022y203.candidatePersonal.web.forms.CandidatePersonalForm;
import com.example.ase2022y203.candidatePersonal.web.forms.CandidatePersonalFormAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/personal")
public class CandidatePersonalController {
    private final CandidatePersonalService candidatePersonalService;
    private final CandidateService candidateService;


    public CandidatePersonalController(CandidatePersonalService svc, CandidateService candidateService) {
        this.candidatePersonalService = svc;
        this.candidateService = candidateService;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView getEditCandidatePersonalInfoForm(@PathVariable("id") Optional<Integer> cid, Model model){

        SingleCandidatePersonalRequest singleCandidatePersonalRequest = SingleCandidatePersonalRequest.of()
                .cid(cid.get()).build();

        var singleCandidatePersonalResponse =  candidatePersonalService
                .getCandidatePersonalByRequest(singleCandidatePersonalRequest);

        if(singleCandidatePersonalResponse.isCandidatePersonalPresent()) {
            var candidatePersonalDTO = singleCandidatePersonalResponse.getCandidatePersonalDTO();

            CandidatePersonalForm candidatePersonalForm = CandidatePersonalFormAssembler
                    .toCandidatePersonalForm(candidatePersonalDTO);

            Optional<CandidateDTO> candidate = candidateService.getCandidateByID(cid.get());

            model.addAttribute("candidate", candidate.get());

            model.addAttribute("candidatePersonalForm", candidatePersonalForm);

            var mv = new ModelAndView("candidate/personal-info-form", model.asMap());
            return mv;
        } else {
            return new ModelAndView("redirect/404", HttpStatus.NOT_FOUND);
        }

    }


}
