package com.example.ase2022y203.candidate.api;

import com.example.ase2022y203.candidate.api.json.CandidateJSON;
import com.example.ase2022y203.candidate.api.json.JSONAssembler;
import com.example.ase2022y203.candidate.service.CandidateDTOReg;
import com.example.ase2022y203.candidate.service.CandidateService;
import com.example.ase2022y203.vettingOfficers.service.VettingOfficersService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("candidate")
public class CandidateAPIController {
    private final CandidateService candidateService;
    private final VettingOfficersService vettingOfficersService;

    public CandidateAPIController(CandidateService svc, VettingOfficersService vos) {
        this.candidateService = svc;
        this.vettingOfficersService = vos;
    }

    @GetMapping("candidatesAPI")
    public ResponseEntity<List<CandidateJSON>> getCandidatesAPI(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN") | request.isUserInRole("ROLE_OFFICER")) {
            List<CandidateDTOReg> CandidateResponse =
                    candidateService.getCandidatesAPI();
            return
                    ResponseEntity.ok(JSONAssembler
                            .toCandidateJsonList(CandidateResponse));
        } else {
            return null;
        }
    }
}

