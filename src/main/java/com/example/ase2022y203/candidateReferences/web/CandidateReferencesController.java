package com.example.ase2022y203.candidateReferences.web;


import com.example.ase2022y203.candidate.service.CandidateDTO;
import com.example.ase2022y203.candidate.service.CandidateService;
import com.example.ase2022y203.candidatePersonal.service.messages.SingleCandidatePersonalRequest;
import com.example.ase2022y203.candidatePersonal.web.forms.CandidatePersonalForm;
import com.example.ase2022y203.candidatePersonal.web.forms.CandidatePersonalFormAssembler;
import com.example.ase2022y203.candidateReferences.service.CandidateReferencesDTO;
import com.example.ase2022y203.candidateReferences.service.CandidateReferencesDTOSave;
import com.example.ase2022y203.candidateReferences.service.CandidateReferencesService;
import com.example.ase2022y203.candidateReferences.service.messages.CandidateRefListRequest;
import com.example.ase2022y203.candidateReferences.service.messages.DeleteRefRequest;
import com.example.ase2022y203.candidateReferences.service.messages.DeleteRefResponse;
import com.example.ase2022y203.candidateReferences.web.forms.ReferenceForm;
import org.springframework.data.web.JsonPath;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/reference")
public class CandidateReferencesController {

    private final CandidateService candidateService;

    private final CandidateReferencesService candidateReferencesService;

    public CandidateReferencesController(CandidateService candidateService, CandidateReferencesService candidateReferencesService) {
        this.candidateService = candidateService;
        this.candidateReferencesService = candidateReferencesService;
    }


    @GetMapping("/add")
    public ModelAndView getNewReference(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipleEmail  = authentication.getName();
        Optional<CandidateDTO> candidate = candidateService.getCandidateByEmail(currentPrincipleEmail);

        model.addAttribute("candidate", candidate.get());
        model.addAttribute("reference", new ReferenceForm());
        var mv = new ModelAndView("references/referenceForm", model.asMap());
        return mv;
    }

    @PostMapping("/save")
    public ModelAndView postNewReference(@Valid @ModelAttribute("reference") ReferenceForm reference, BindingResult bindingResult, Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipleEmail  = authentication.getName();
        Optional<CandidateDTO> candidate = candidateService.getCandidateByEmail(currentPrincipleEmail);

        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(System.out::println);
            model.addAttribute("reference", reference);
            model.addAttribute("candidate", candidate.get());
            return new ModelAndView("references/referenceForm");
        } else {
            CandidateReferencesDTOSave referenceDTO = new CandidateReferencesDTOSave(
                    candidate.get().getId(), reference.getReferee_name(),
                    reference.getReferee_phone_number()
            );

            try{
                candidateReferencesService.addNewReference(referenceDTO);
            } catch(Exception e){
                System.out.println(e.getMessage());
                model.addAttribute("reference", reference);
                model.addAttribute("candidate", candidate.get());
                return new ModelAndView("references/referenceForm", model.asMap());
            }

            return new ModelAndView("redirect:/candidate/candidate-profile");
        }
    }

    @GetMapping("edit/{id}")
    public ModelAndView getEditReferenceForm(@PathVariable("id") Integer index, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipleEmail  = authentication.getName();
        Optional<CandidateDTO> candidate = candidateService.getCandidateByEmail(currentPrincipleEmail);

        if (candidate.isPresent()) {
            List<CandidateReferencesDTO> candidateReferencesDTOS;

            CandidateRefListRequest candidateRefListRequest = CandidateRefListRequest
                    .of()
                    .cid(candidate.get().getId())
                    .build();

            var candidateRefListResponse =  candidateReferencesService
                    .getCandidateReferencesByCID(candidateRefListRequest.getCid());

            Optional<CandidateDTO> candidateDTO = candidateService.getCandidateByID(candidate.get().getId());

            model.addAttribute("candidate", candidateDTO.get());

            model.addAttribute("reference", candidateRefListResponse.get(index));

            var mv = new ModelAndView("references/editReferenceForm", model.asMap());
            return mv;
        } else {
            return new ModelAndView("error/404", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("delete/{id}")
    public ModelAndView deleteReference(@PathVariable("id") Integer index, Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipleEmail  = authentication.getName();
        Optional<CandidateDTO> candidate = candidateService.getCandidateByEmail(currentPrincipleEmail);


        if (candidate.isPresent()) {

            CandidateRefListRequest candidateRefListRequest = CandidateRefListRequest
                    .of()
                    .cid(candidate.get().getId())
                    .build();

            var candidateRefListResponse = candidateReferencesService
                    .getCandidateReferencesByCID(candidateRefListRequest.getCid());


           candidateReferencesService.deleteReference(candidateRefListResponse.get(index));
        } else{

        }
        var mv = new ModelAndView("redirect:/candidate/candidate-profile", model.asMap());
        return mv;
    }


    @PostMapping("/save/{id}")
    public ModelAndView postEditReference(@PathVariable("id") Integer index, @Valid @ModelAttribute("reference") ReferenceForm reference, BindingResult bindingResult, Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipleEmail  = authentication.getName();
        Optional<CandidateDTO> candidate = candidateService.getCandidateByEmail(currentPrincipleEmail);

        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(System.out::println);
            model.addAttribute("reference", reference);
            model.addAttribute("candidate", candidate.get());
            return new ModelAndView("references/editReferenceForm");
        } else {
            CandidateReferencesDTO referenceDTO = new CandidateReferencesDTO(
                    reference.getId(),
                    candidate.get().getId(), reference.getReferee_name(),
                    reference.getReferee_phone_number()
            );

            try{
                candidateReferencesService.updateReference(referenceDTO);
            } catch(Exception e){
                System.out.println(e.getMessage());
                model.addAttribute("reference", reference);
                model.addAttribute("candidate", candidate.get());
                return new ModelAndView("references/editReferenceForm", model.asMap());
            }

            return new ModelAndView("redirect:/candidate/candidate-profile");
        }
    }


}


