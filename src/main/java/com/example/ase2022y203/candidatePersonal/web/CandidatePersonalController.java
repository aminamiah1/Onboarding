package com.example.ase2022y203.candidatePersonal.web;

import com.example.ase2022y203.candidate.service.CandidateDTO;
import com.example.ase2022y203.candidate.service.CandidateService;
import com.example.ase2022y203.candidatePersonal.service.CandidatePersonalDTO;
import com.example.ase2022y203.candidatePersonal.service.CandidatePersonalService;
import com.example.ase2022y203.candidatePersonal.service.messages.SaveCandidatePersonalRequest;
import com.example.ase2022y203.candidatePersonal.service.messages.SaveCandidatePersonalResponse;
import com.example.ase2022y203.candidatePersonal.service.messages.SingleCandidatePersonalRequest;
import com.example.ase2022y203.candidatePersonal.web.forms.CandidatePersonalForm;
import com.example.ase2022y203.candidatePersonal.web.forms.CandidatePersonalFormAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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

    @GetMapping("/edit")
    public ModelAndView getEditCandidatePersonalInfoForm(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipleEmail  = authentication.getName();
        Optional<CandidateDTO> candidate = candidateService.getCandidateByEmail(currentPrincipleEmail);

        SingleCandidatePersonalRequest singleCandidatePersonalRequest = SingleCandidatePersonalRequest.of()
                .cid(candidate.get().getId()).build();

        var singleCandidatePersonalResponse = candidatePersonalService
                .getCandidatePersonalByRequest(singleCandidatePersonalRequest);

        if (singleCandidatePersonalResponse.isCandidatePersonalPresent()) {
            var candidatePersonalDTO = singleCandidatePersonalResponse.getCandidatePersonalDTO();

            CandidatePersonalForm candidatePersonalForm = CandidatePersonalFormAssembler
                    .toCandidatePersonalForm(candidatePersonalDTO);

            Optional<CandidateDTO> candidateDTO = candidateService.getCandidateByID(candidate.get().getId());

            model.addAttribute("candidate", candidateDTO.get());

            model.addAttribute("candidatePersonalForm", candidatePersonalForm);

            var mv = new ModelAndView("candidate/personal-info-form", model.asMap());
            return mv;
        } else {
            return new ModelAndView("redirect/404", HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/{id}/save")
    public ModelAndView SaveCandidatePersonalInfo(@Valid @ModelAttribute("candidatePersonalForm") CandidatePersonalForm newCandidatePersonal,BindingResult bindingResult, Model model,
                                                  @PathVariable("id") Optional<Integer> cid) {
        if (bindingResult.hasErrors()) {

            SingleCandidatePersonalRequest singleCandidatePersonalRequest =
                    SingleCandidatePersonalRequest.of().cid(newCandidatePersonal.getC_id()).build();

            var singleCandidatePersonalResponse = candidatePersonalService
                    .getCandidatePersonalByRequest(singleCandidatePersonalRequest);

            var candidatePersonalDTO = singleCandidatePersonalResponse.getCandidatePersonalDTO();

            CandidatePersonalForm candidatePersonalForm = CandidatePersonalFormAssembler
                    .toCandidatePersonalForm(candidatePersonalDTO);

            Optional<CandidateDTO> candidate = candidateService.getCandidateByID(cid.get());

            model.addAttribute("candidate", candidate.get());

            model.addAttribute("candidatePersonalForm", newCandidatePersonal);

            return new ModelAndView("candidate/personal-info-form", model.asMap());
        } else {
            CandidatePersonalDTO candidatePersonalDTO = new CandidatePersonalDTO(
                    newCandidatePersonal.getId(), newCandidatePersonal.getC_id(),
                    newCandidatePersonal.getNational_insurance(), newCandidatePersonal.getEthnicity(),
                    newCandidatePersonal.getGender(), newCandidatePersonal.getAge(), newCandidatePersonal.getTelephone_number());

            SaveCandidatePersonalRequest saveCandidatePersonalRequest = SaveCandidatePersonalRequest.of()
                    .candidatePersonalDTO(candidatePersonalDTO).build();

            try{
                SaveCandidatePersonalResponse saveCandidatePersonalResponse = candidatePersonalService
                        .process(saveCandidatePersonalRequest);
            }catch (Exception e) {
                System.out.println(e.getMessage());
            };

            var mv = new ModelAndView("redirect:/candidate/candidate-profile");
            return mv;

        }
    }
}
