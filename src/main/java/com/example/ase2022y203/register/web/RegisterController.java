package com.example.ase2022y203.register.web;

import com.example.ase2022y203.candidate.service.CandidateDTO;
import com.example.ase2022y203.candidate.service.CandidateService;
import com.example.ase2022y203.register.service.RegisterDTO;
import com.example.ase2022y203.register.service.RegisterService;
import com.example.ase2022y203.register.service.messages.SaveRegisterRequest;
import com.example.ase2022y203.register.service.messages.SaveRegistersResponse;
import com.example.ase2022y203.register.service.messages.SingleRegisterRequest;
import com.example.ase2022y203.register.web.forms.RegistersForm;
import com.example.ase2022y203.register.web.forms.RegistersFormAssembler;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final RegisterService registerService;
    private final CandidateService candidateService;

    public RegisterController(RegisterService svc, CandidateService cvs) {
        this.registerService = svc;
        this.candidateService = cvs;
    }

    @GetMapping("/{id}/get")
    public ModelAndView getRegisterInfo(@PathVariable("id") Optional<Integer> id, Model model) {

        SingleRegisterRequest singleRegisterRequest = SingleRegisterRequest.of()
                .id(id.get()).build();

        var singleRegisterResponse = registerService
                .getRegistersByRequest(singleRegisterRequest);

        if (singleRegisterResponse.isRegisterPresent()) {

            var registerDTO = singleRegisterResponse.getRegisterDTO();
            RegistersForm registersForm = RegistersFormAssembler
                    .toRegistersForm(registerDTO);

            Optional<CandidateDTO> candidateDTO = candidateService.getCandidateByID(id.get());
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
                                         BindingResult bindingResult, Model model, @PathVariable("id") Optional<Integer> id) {
        if (bindingResult.hasErrors()) {
            SingleRegisterRequest singleRegisterRequest =
                    SingleRegisterRequest.of().id(newRegister.getID()).build();

            var singleRegisterResponse = registerService.getRegistersByRequest(singleRegisterRequest);
            var registerDTO = singleRegisterResponse.getRegisterDTO();

            RegistersForm registersForm = RegistersFormAssembler.toRegistersForm(registerDTO);

            Optional<CandidateDTO> candidateDTO = candidateService.getCandidateByID(id.get());
            model.addAttribute("candidate", candidateDTO.get());
            model.addAttribute("registersForm", newRegister);

            return new ModelAndView("registration/registrationForm", model.asMap());
        } else {
            RegisterDTO registerDTO = new RegisterDTO(
                    newRegister.getID(), newRegister.getFirst_name(),
                    newRegister.getSurname(), newRegister.getEmail(), newRegister.getPassword());

            SaveRegisterRequest saveRegisterRequest = SaveRegisterRequest.of().
                    registerDTO(registerDTO).build();
            SaveRegistersResponse saveRegistersResponse = registerService.
                    process(saveRegisterRequest);

            var mv = new ModelAndView("redirect:/registration/successPage/" + id.get());
            return mv;
        }
    }
}
