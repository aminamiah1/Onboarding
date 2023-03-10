package com.example.ase2022y203.candidate.web;

import com.example.ase2022y203.candidate.web.forms.RegistersForm;
import com.example.ase2022y203.candidate.service.*;
import com.example.ase2022y203.candidate.service.CandidateService;
import com.example.ase2022y203.candidateDocuments.service.DocumentsDTO;
import com.example.ase2022y203.candidateDocuments.service.DocumentsService;
import com.example.ase2022y203.candidatePersonal.service.CandidatePersonalDTO;
import com.example.ase2022y203.candidatePersonal.service.CandidatePersonalService;
import com.example.ase2022y203.candidateReferences.service.CandidateReferencesService;
import com.example.ase2022y203.candidateReferences.service.messages.CandidateRefListRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final CandidatePersonalService candidatePersonalService;

    private final CandidateReferencesService candidateReferencesService;

    private final DocumentsService documentsService;

    public CandidateController(CandidateService svc, CandidatePersonalService cps, CandidateReferencesService crs, DocumentsService documentsService) {
        this.candidateService = svc;
        this.candidatePersonalService = cps;
        this.candidateReferencesService = crs;
        this.documentsService = documentsService;
    }

    @GetMapping("candidate-profile")
    public ModelAndView getCandidate(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipleEmail = authentication.getName();

        Optional<CandidateDTO> candidate = candidateService.getCandidateByEmail(currentPrincipleEmail);
        Optional<CandidatePersonalDTO> candidatePersonal = candidatePersonalService
                .getCandidatePersonalByCID(candidate.get().getId());

        CandidateRefListRequest candidateRefListRequest = CandidateRefListRequest
                .of()
                .cid(candidate.get().getId())
                .build();

        var candidateRefListResponse = candidateReferencesService
                .getCandidateReferencesByCID(candidateRefListRequest.getCid());

        if (candidate.isPresent() & candidatePersonal.get().getAge() != null &
                candidatePersonal.get().getEthnicity() != null
                & candidatePersonal.get().getGender() != null
                & candidatePersonal.get().getNational_insurance() != null
                & candidatePersonal.get().getTelephone_number() != null) {
            model.addAttribute("candidate", candidate.get());
            model.addAttribute("candidatePersonal", candidatePersonal.get());
            model.addAttribute("references", candidateRefListResponse);
            var mv = new ModelAndView("candidate/candidate-profile", model.asMap());
            return mv;
        } else if (candidate.isPresent() & candidatePersonal.get().getAge() == null
                & candidatePersonal.get().getGender() == null
                & candidatePersonal.get().getEthnicity() == null
                & candidatePersonal.get().getTelephone_number() == null
                & candidatePersonal.get().getNational_insurance() == null) {
            return new ModelAndView("redirect:/personal/edit");
        } else {
            return new ModelAndView("redirect:/404");
        }
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
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

            CandidateDTOReg candidate = new CandidateDTOReg(register.getFirst_name(), register.getSurname(),
                    register.getEmail(), bCryptPasswordEncoder.encode(register.getPassword()), register.getCompany_name());
            try {
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

    @GetMapping("document-portal")
    public ModelAndView getDocumentPortal(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipleEmail = authentication.getName();

        Optional<CandidateDTO> candidate = candidateService.getCandidateByEmail(currentPrincipleEmail);

        String idFileName = "ID_" + "C" + candidate.get().getId().toString() + ".jpg";

        String passportFileName = "PP_" + "C" + candidate.get().getId().toString() + ".jpg";

        Optional<DocumentsDTO> idFile = documentsService.getDocument(idFileName);
        Optional<DocumentsDTO> passportFile = documentsService.getDocument(passportFileName);

        if(candidate.isPresent() & idFile.isPresent() & passportFile.isPresent()){
            model.addAttribute("candidate", candidate.get());
            model.addAttribute("idFile", idFile.get());
            model.addAttribute("passportFile", passportFile.get());
            var mv = new ModelAndView("candidate/document-portal", model.asMap());
            return mv;
        } else if(candidate.isPresent() & idFile.isPresent()) {
            model.addAttribute("candidate", candidate.get());
            model.addAttribute("idFile", idFile.get());
            var mv = new ModelAndView("candidate/document-portal", model.asMap());
            return mv;
        } else if(candidate.isPresent() & passportFile.isPresent()){
            model.addAttribute("candidate", candidate.get());
            model.addAttribute("passportFile", passportFile.get());
            var mv = new ModelAndView("candidate/document-portal", model.asMap());
            return mv;
        } else if(candidate.isPresent()){
            model.addAttribute("candidate", candidate.get());
            var mv = new ModelAndView("candidate/document-portal", model.asMap());
            return mv;
        } else {
            return new ModelAndView("redirect:/404");
        }
    }

}