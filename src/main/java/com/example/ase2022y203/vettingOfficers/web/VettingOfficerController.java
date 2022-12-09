package com.example.ase2022y203.vettingOfficers.web;


import com.example.ase2022y203.candidate.service.CandidateDTO;
import com.example.ase2022y203.candidate.service.CandidateService;
import com.example.ase2022y203.candidate.service.messages.CandidateListRequest;
import com.example.ase2022y203.candidate.service.messages.CandidateListResponse;
import com.example.ase2022y203.candidatePersonal.service.CandidatePersonalDTO;
import com.example.ase2022y203.candidatePersonal.service.CandidatePersonalService;
import com.example.ase2022y203.candidateReferences.service.CandidateReferencesService;
import com.example.ase2022y203.candidateReferences.service.messages.CandidateRefListRequest;
import com.example.ase2022y203.vettingOfficers.service.VettingOfficersDTO;
import com.example.ase2022y203.vettingOfficers.service.VettingOfficersService;
import com.example.ase2022y203.vettingOfficers.service.messages.OfficersListRequest;
import com.example.ase2022y203.vettingOfficers.service.messages.OfficersListResponse;
import com.example.ase2022y203.vettingOfficers.web.forms.VettingOfficerForm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("officer")
public class VettingOfficerController {
    private final CandidateService candidateService;

    private final VettingOfficersService vettingOfficersService;

    private final CandidatePersonalService candidatePersonalService;

    private final CandidateReferencesService candidateReferencesService;

    public VettingOfficerController(CandidateService candidateService, VettingOfficersService vos, CandidatePersonalService candidatePersonalService, CandidateReferencesService candidateReferencesService) {
        this.candidateService = candidateService;
        this.vettingOfficersService = vos;
        this.candidatePersonalService = candidatePersonalService;
        this.candidateReferencesService = candidateReferencesService;
    }

    @GetMapping("officer-profile")
    public ModelAndView getVettingOfficer(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipleEmail = authentication.getName();

        Optional<VettingOfficersDTO> vettingOfficer = vettingOfficersService.getVettingOfficerByEmail(currentPrincipleEmail);

        CandidateListRequest candidateListRequest = CandidateListRequest
                .of()
                .build();

        CandidateListResponse candidateListResponse = candidateService.getCandidates(candidateListRequest);

        if (vettingOfficer.isPresent()) {
            model.addAttribute("officer", vettingOfficer.get());
            model.addAttribute("candidates", candidateListResponse.getCandidates());
            var mv = new ModelAndView("officer/officer-profile", model.asMap());
            return mv;
        } else {
            return new ModelAndView("redirect:/404");
        }
    }

    @GetMapping("view-candidates")
    public ModelAndView getAllCandidates(Model model, HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN") | request.isUserInRole("ROLE_OFFICER")) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipleEmail = authentication.getName();
            Optional<VettingOfficersDTO> vettingOfficer = vettingOfficersService.getVettingOfficerByEmail(currentPrincipleEmail);
            CandidateListRequest candidateListRequest = CandidateListRequest
                    .of()
                    .build();
            CandidateListResponse candidateListResponse = candidateService.getCandidates(candidateListRequest);
            System.out.println(candidateListResponse.getCandidates().size());
            model.addAttribute("candidates", candidateListResponse.getCandidates());
            model.addAttribute("officer", vettingOfficer.get());
            var mv = new ModelAndView("officer/officer-candidates", model.asMap());
            return mv;
        } else {
            return new ModelAndView("redirect:/404");
        }
    }

    @GetMapping("view-details")
    public ModelAndView getAllDetails(Model model, HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN") | request.isUserInRole("ROLE_OFFICER")) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipleEmail = authentication.getName();
            Optional<VettingOfficersDTO> vettingOfficer = vettingOfficersService.getVettingOfficerByEmail(currentPrincipleEmail);
            CandidateListRequest candidateListRequest = CandidateListRequest
                    .of()
                    .build();
            CandidateListResponse candidateListResponse = candidateService.getCandidates(candidateListRequest);
            List<CandidatePersonalDTO> candidatePersonal = candidatePersonalService.findAllPersonal();
            System.out.println(candidateListResponse.getCandidates().size());
            model.addAttribute("candidates", candidateListResponse.getCandidates());
            model.addAttribute("candidatePersonal", candidatePersonal);
            model.addAttribute("officer", vettingOfficer.get());
            var mv = new ModelAndView("officer/officer-personal", model.asMap());
            return mv;
        } else {
            return new ModelAndView("redirect:/404");
        }
    }

    @GetMapping("all-officers")
    public ModelAndView getOfficers(Model model, HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            OfficersListRequest officersListRequest = OfficersListRequest
                    .of()
                    .build();
            OfficersListResponse officersListResponse = vettingOfficersService.getOfficers(officersListRequest);
            System.out.println(officersListResponse.getOfficersDTOS());
            model.addAttribute("officers", officersListResponse.getOfficersDTOS());
            var mv = new ModelAndView("officer/all-officers", model.asMap());
            return mv;
        } else {
            return new ModelAndView("redirect:/404");
        }
    }

    @GetMapping("add")
    public ModelAndView getNewOfficers(Model model, HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            model.addAttribute("VettingOfficerForm", new VettingOfficerForm());
            var mv = new ModelAndView("admin/addOfficers", model.asMap());
            return mv;
        } else {
            return new ModelAndView("redirect:/404");
        }
    }

    @PostMapping("saveNewOfficers")
    public ModelAndView postNewOfficers(@Valid @ModelAttribute("VettingOfficerForm") VettingOfficerForm
                                                officerForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            model.addAttribute("VettingOfficerForm", officerForm);
            return new ModelAndView("admin/addOfficers", model.asMap());
        } else {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

            VettingOfficersDTO officersDTO = new VettingOfficersDTO(officerForm.getID(), officerForm.getFirst_name(), officerForm.getSurname(),
                    officerForm.getEmail(), bCryptPasswordEncoder.encode(officerForm.getPassword()));

            try {
                vettingOfficersService.addNewAdmin(officersDTO);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                model.addAttribute("VettingOfficerForm", officerForm);
                return new ModelAndView("admin/addOfficers", model.asMap());
            }
            var mv = new ModelAndView("redirect:/successPage2");
            return mv;
        }
    }

    @GetMapping("view-profile/{id}")
    public ModelAndView getCandidate(@PathVariable Integer id, Model model, HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN") | request.isUserInRole("ROLE_OFFICER")) {
            Optional<CandidateDTO> candidate = candidateService.getCandidateByID(id);
            Optional<CandidatePersonalDTO> candidatePersonal = candidatePersonalService.getCandidatePersonalByCID(id);
            var candidateRefListResponse = candidateReferencesService
                    .getCandidateReferencesByCID(id);
            if (candidate.isPresent()) {
                model.addAttribute("candidate", candidate.get());
                model.addAttribute("candidatePersonal", candidatePersonal.get());
                model.addAttribute("references", candidateRefListResponse);
                var mv = new ModelAndView("officer/officer-candidate", model.asMap());
                return mv;
            } else {
                return new ModelAndView("redirect:/404");
            }
        } else {
            return new ModelAndView("redirect:/404");
        }
    }

    @GetMapping("view-applications")
    public ModelAndView getApplicationDashboard(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipleEmail = authentication.getName();

        Optional<VettingOfficersDTO> vettingOfficer = vettingOfficersService.getVettingOfficerByEmail(currentPrincipleEmail);

        if (vettingOfficer.isPresent()) {
            model.addAttribute("officer", vettingOfficer.get());
            var mv = new ModelAndView("officer/officer-applications", model.asMap());
            return mv;
        } else {
            return new ModelAndView("redirect:/404");
        }
    }

    @GetMapping("all-applications")
    public ModelAndView getAllApplications(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipleEmail = authentication.getName();

        Optional<VettingOfficersDTO> vettingOfficer = vettingOfficersService.getVettingOfficerByEmail(currentPrincipleEmail);

        CandidateListRequest candidateListRequest = CandidateListRequest
                .of()
                .build();
        CandidateListResponse candidateListResponse = candidateService.getCandidates(candidateListRequest);
        model.addAttribute("candidates", candidateListResponse.getCandidates());

        Optional<ApplicationsDTO> applications =

    }

}