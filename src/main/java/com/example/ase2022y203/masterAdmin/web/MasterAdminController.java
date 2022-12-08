package com.example.ase2022y203.masterAdmin.web;

import com.example.ase2022y203.candidate.service.CandidateService;
import com.example.ase2022y203.candidate.service.messages.CandidateListRequest;
import com.example.ase2022y203.candidate.service.messages.CandidateListResponse;
import com.example.ase2022y203.candidatePersonal.service.CandidatePersonalDTO;
import com.example.ase2022y203.candidatePersonal.service.CandidatePersonalService;
import com.example.ase2022y203.masterAdmin.service.MasterAdminDTO;
import com.example.ase2022y203.masterAdmin.service.MasterAdminService;
import com.example.ase2022y203.vettingOfficers.service.VettingOfficersDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin")
public class MasterAdminController {

    private final MasterAdminService masterAdminService;

    private final CandidateService candidateService;

    private final CandidatePersonalService candidatePersonalService;

    public MasterAdminController(MasterAdminService masterAdminService, CandidateService candidateService, CandidatePersonalService candidatePersonalService) {
        this.masterAdminService = masterAdminService;
        this.candidateService = candidateService;
        this.candidatePersonalService = candidatePersonalService;
    }

    @GetMapping("admin-profile")
    public ModelAndView getMasterAdmin(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipleEmail = authentication.getName();

        Optional<MasterAdminDTO> masterAdmin =  masterAdminService.getMasterAdminByEmail(currentPrincipleEmail);

        if(masterAdmin.isPresent()){
            model.addAttribute("admin", masterAdmin.get());
            var mv = new ModelAndView("admin/admin-profile", model.asMap());
            return mv;
        } else {
            return new ModelAndView("redirect:/404");
        }
    }

    @GetMapping("view-details")
    public ModelAndView getAllDetails(Model model, HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipleEmail = authentication.getName();
            Optional<MasterAdminDTO> masterAdmin =  masterAdminService.getMasterAdminByEmail(currentPrincipleEmail);
            CandidateListRequest candidateListRequest = CandidateListRequest
                    .of()
                    .build();
            CandidateListResponse candidateListResponse = candidateService.getCandidates(candidateListRequest);
            List<CandidatePersonalDTO> candidatePersonal = candidatePersonalService.findAllPersonal();
            System.out.println(candidateListResponse.getCandidates().size());
            model.addAttribute("candidates", candidateListResponse.getCandidates());
            model.addAttribute("candidatePersonal", candidatePersonal);
            model.addAttribute("admin", masterAdmin.get());
            var mv = new ModelAndView("admin/admin-personal", model.asMap());
            return mv;
        } else {
            return new ModelAndView("redirect:/404");
        }
    }

    @GetMapping("view-candidates")
    public ModelAndView getAllCandidates(Model model, HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipleEmail = authentication.getName();
            Optional<MasterAdminDTO> masterAdmin = masterAdminService.getMasterAdminByEmail(currentPrincipleEmail);
            CandidateListRequest candidateListRequest = CandidateListRequest
                    .of()
                    .build();
            CandidateListResponse candidateListResponse = candidateService.getCandidates(candidateListRequest);
            System.out.println(candidateListResponse.getCandidates().size());
            model.addAttribute("candidates", candidateListResponse.getCandidates());
            model.addAttribute("admin", masterAdmin.get());
            var mv = new ModelAndView("admin/admin-candidates", model.asMap());
            return mv;
        } else {
            return new ModelAndView("redirect:/404");
        }
    }
}
