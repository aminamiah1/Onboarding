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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("view-profile/{id}")
    public ModelAndView getCandidate(@PathVariable Integer id, Model model, HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN") | request.isUserInRole("ROLE_OFFICER")) {
            Optional<CandidateDTO> candidate = candidateService.getCandidateByID(id);
            Optional<CandidatePersonalDTO> candidatePersonal = candidatePersonalService.getCandidatePersonalByCID(id);
            var candidateRefListResponse =  candidateReferencesService
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
}
