package com.example.ase2022y203.candidate.web;


import com.example.ase2022y203.candidate.service.messages.SaveCandidateRequest;
import com.example.ase2022y203.candidate.service.messages.SaveCandidateResponse;
import com.example.ase2022y203.candidate.service.messages.SingleCandidateRequest;
import com.example.ase2022y203.candidate.web.forms.RegistersForm;
import com.example.ase2022y203.candidate.web.forms.RegistersFormAssembler;
import com.example.ase2022y203.candidate.service.*;
import com.example.ase2022y203.candidate.service.CandidateService;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/{id}/get")
    public ModelAndView getRegisterInfo(@PathVariable("id") Optional<Integer> cid, Model model) {

        SingleCandidateRequest singleCandidateRequest = SingleCandidateRequest.of()
                .cid(cid.get()).build();

        var singleCandidateResponse = candidateService
                .getCandidatesByRequest(singleCandidateRequest);

        if (singleCandidateResponse.isCandidatePresent()) {
            var candidatesDTO = singleCandidateResponse.getCandidateDTO();
            RegistersForm registersForm = RegistersFormAssembler
                    .toRegistersForm(candidatesDTO);

            Optional<CandidateDTO> candidateDTO = candidateService.getCandidateByID(cid.get());
            model.addAttribute("candidate", candidateDTO.get());
            model.addAttribute("Register Form", registersForm);

            var mv = new ModelAndView("redirect:/registration/registrationForm" + model.asMap());
            return mv;
        } else {
            return new ModelAndView("redirect/404", HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/{id}/save")
    public ModelAndView SaveRegisterInfo(@Valid @ModelAttribute("RegistersForm") RegistersForm newRegister,
                                         BindingResult bindingResult, Model model, @PathVariable("id") Optional<Integer> cid) {
        if (bindingResult.hasErrors()) {
            SingleCandidateRequest singleCandidateRequest =
                    SingleCandidateRequest.of().id(newRegister.getID()).build();

            var singleCandidateResponse = candidateService.getCandidatesByRequest(singleCandidateRequest);
            var registerDTO = singleCandidateResponse.getCandidateDTO();

            RegistersForm registersForm = RegistersFormAssembler.toRegistersForm(registerDTO);

            Optional<CandidateDTO> candidateDTO = candidateService.getCandidateByID(cid.get());
            model.addAttribute("candidate", candidateDTO.get());
            model.addAttribute("registersForm", newRegister);

            return new ModelAndView("registration/registrationForm", model.asMap());
        } else {
            CandidateDTO candidateDTO = new CandidateDTO(
                    newRegister.getID(), newRegister.getC_id(), newRegister.getFirst_name(),
                    newRegister.getSurname(), newRegister.getEmail(), newRegister.getPassword(), newRegister.getCompany_name());

            SaveCandidateRequest saveCandidateRequest = SaveCandidateRequest.of().
                    candidateDTO(candidateDTO).build();
            SaveCandidateResponse saveCandidateResponse = candidateService.
                    process(saveCandidateRequest);

            var mv = new ModelAndView("redirect:/registration/successPage/" + cid.get());
            return mv;
        }
    }
}