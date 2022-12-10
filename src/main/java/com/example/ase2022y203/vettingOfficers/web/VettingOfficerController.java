package com.example.ase2022y203.vettingOfficers.web;


import com.example.ase2022y203.applications.service.ApplicationsDTO;
import com.example.ase2022y203.applications.service.ApplicationsService;
import com.example.ase2022y203.applications.service.messages.ApplicationsListRequest;
import com.example.ase2022y203.applications.service.messages.ApplicationsListResponse;
import com.example.ase2022y203.applications.web.forms.ApplicationsForm;
import com.example.ase2022y203.applications.web.forms.ApplicationsFormAssembler;
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

    private final ApplicationsService applicationsService;

    public VettingOfficerController(CandidateService candidateService, VettingOfficersService vos, CandidatePersonalService candidatePersonalService, CandidateReferencesService candidateReferencesService, ApplicationsService applicationsService) {
        this.candidateService = candidateService;
        this.vettingOfficersService = vos;
        this.candidatePersonalService = candidatePersonalService;
        this.candidateReferencesService = candidateReferencesService;
        this.applicationsService = applicationsService;
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
            var mv = new ModelAndView("redirect:/successAdmin");
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
    public ModelAndView getAllApplications(Model model, HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN") | request.isUserInRole("ROLE_OFFICER")) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipleEmail = authentication.getName();

            Optional<VettingOfficersDTO> vettingOfficer = vettingOfficersService.getVettingOfficerByEmail(currentPrincipleEmail);
            model.addAttribute("officer", vettingOfficer.get());

            ApplicationsListRequest applicationsListRequest = ApplicationsListRequest
                    .of()
                    .build();
            ApplicationsListResponse applicationsListResponse = applicationsService.getApplications(applicationsListRequest);
            model.addAttribute("applications", applicationsListResponse.getApplications());

            var mv = new ModelAndView("officer/officer-all-applications", model.asMap());
            return mv;
        } else {
            return new ModelAndView("redirect:/404");
        }
    }

    @GetMapping("pending-applications")
    public ModelAndView getPendingApplications(Model model, HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN") | request.isUserInRole("ROLE_OFFICER")) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipleEmail = authentication.getName();

            Optional<VettingOfficersDTO> vettingOfficer = vettingOfficersService.getVettingOfficerByEmail(currentPrincipleEmail);
            model.addAttribute("officer", vettingOfficer.get());

            ApplicationsListRequest applicationsListRequest = ApplicationsListRequest
                    .of()
                    .build();
            ApplicationsListResponse applicationsListResponse = applicationsService.getPendingApplications(applicationsListRequest);
            model.addAttribute("applications", applicationsListResponse.getApplications());

            var mv = new ModelAndView("officer/officer-pending-applications", model.asMap());
            return mv;
        } else {
            return new ModelAndView("redirect:/404");
        }
    }

    @GetMapping("denied-applications")
    public ModelAndView getDeniedApplications(Model model, HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN") | request.isUserInRole("ROLE_OFFICER")) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipleEmail = authentication.getName();

            Optional<VettingOfficersDTO> vettingOfficer = vettingOfficersService.getVettingOfficerByEmail(currentPrincipleEmail);
            model.addAttribute("officer", vettingOfficer.get());

            ApplicationsListRequest applicationsListRequest = ApplicationsListRequest
                    .of()
                    .build();
            ApplicationsListResponse applicationsListResponse = applicationsService.getDeniedApplications(applicationsListRequest);
            model.addAttribute("applications", applicationsListResponse.getApplications());

            var mv = new ModelAndView("officer/officer-denied-applications", model.asMap());
            return mv;
        } else {
            return new ModelAndView("redirect:/404");
        }
    }

    @GetMapping("approved-applications")
    public ModelAndView getApprovedApplications(Model model, HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN") | request.isUserInRole("ROLE_OFFICER")) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipleEmail = authentication.getName();

            Optional<VettingOfficersDTO> vettingOfficer = vettingOfficersService.getVettingOfficerByEmail(currentPrincipleEmail);
            model.addAttribute("officer", vettingOfficer.get());

            ApplicationsListRequest applicationsListRequest = ApplicationsListRequest
                    .of()
                    .build();
            ApplicationsListResponse applicationsListResponse = applicationsService.getApprovedApplications(applicationsListRequest);
            model.addAttribute("applications", applicationsListResponse.getApplications());

            var mv = new ModelAndView("officer/officer-approved-applications", model.asMap());
            return mv;
        } else {
            return new ModelAndView("redirect:/404");
        }
    }

    @GetMapping("editstatus/{id}")
    public ModelAndView getApplicationStatusForm(@PathVariable("id") Optional<Integer> id, Model model, HttpServletRequest request){
        if (request.isUserInRole("ROLE_ADMIN") | request.isUserInRole("ROLE_OFFICER")) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipleEmail = authentication.getName();

            Optional<VettingOfficersDTO> vettingOfficer = vettingOfficersService.getVettingOfficerByEmail(currentPrincipleEmail);


            Optional<ApplicationsDTO> application = applicationsService.getApplicationByID(id);

            if (application.isPresent()){
                var applicationDTO = application.get();
                ApplicationsForm applicationsForm = ApplicationsFormAssembler.toApplicationsForm(applicationDTO);
                model.addAttribute("vettingOfficer", vettingOfficer.get());
                model.addAttribute("app", applicationDTO);
                model.addAttribute("applicationsForm", applicationsForm);
                var mv = new ModelAndView("officer/officer-edit-status", model.asMap());
                return mv;
            } else {
                return new ModelAndView("redirect:/404");
            }

        } else {
            return new ModelAndView("redirect:/404");
        }
    }

    @PostMapping("savestatus/{id}")
    public ModelAndView saveApplicationStatus(@Valid @ModelAttribute("applicationsForm") ApplicationsForm applicationsForm
    , BindingResult bindingResult, Model model, @PathVariable("id") Optional<Integer> id) {
        if(bindingResult.hasErrors()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipleEmail = authentication.getName();
            Optional<VettingOfficersDTO> vettingOfficer = vettingOfficersService.getVettingOfficerByEmail(currentPrincipleEmail);

            Optional<ApplicationsDTO> application = applicationsService.getApplicationByID(id);
            var applicationDTO = application.get();

            model.addAttribute("vettingOfficer", vettingOfficer.get());
            model.addAttribute("app", applicationDTO);
            model.addAttribute("applicationsForm", applicationsForm);

            return new ModelAndView("officer/officer-edit-status", model.asMap());
        } else {
            ApplicationsDTO applicationsDTO = new ApplicationsDTO(
                    applicationsForm.getId(), applicationsForm.getAppstatus(), applicationsForm.getCid()
            );

            try{
                applicationsService.updateStatus(applicationsDTO);
            } catch (Exception e){
                System.out.println(e.getMessage());
                model.addAttribute("app", applicationsDTO);
                model.addAttribute("ApplicationsForm", applicationsForm);
                return new ModelAndView("officer/officer-edit-status", model.asMap());
               }

            var mv = new ModelAndView("redirect:/officer/all-applications");
            return mv;
        }
    }

    @PostMapping("deletestatus/{id}")
    public ModelAndView deleteReference(@PathVariable("id") Optional<Integer> id, Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipleEmail = authentication.getName();
        Optional<VettingOfficersDTO> vettingOfficer = vettingOfficersService.getVettingOfficerByEmail(currentPrincipleEmail);

        if (vettingOfficer.isPresent()) {

            Optional<ApplicationsDTO> application = applicationsService.getApplicationByID(id);
            var applicationDTO = application.get();

            applicationsService.delete(applicationDTO);
        } else{
            return new ModelAndView("redirect:/404");
        }

        var mv = new ModelAndView("redirect:/officer/all-applications");
        return mv;
    }

}