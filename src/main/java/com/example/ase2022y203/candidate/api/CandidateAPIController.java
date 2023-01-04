package com.example.ase2022y203.candidate.api;

import com.example.ase2022y203.candidate.api.json.JSON;
import com.example.ase2022y203.candidate.api.json.JSONAssembler;
import com.example.ase2022y203.candidate.service.CandidateService;
import com.example.ase2022y203.candidate.service.messages.CandidateListRequest;
import com.example.ase2022y203.candidate.service.messages.CandidateListResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("candidate")
public class CandidateAPIController {
    private final CandidateService candidateService;

    public CandidateAPIController(CandidateService svc) {
        this.candidateService = svc;
    }

    @GetMapping("candidatesAPI")
    public ResponseEntity<List<JSON>> getCandidatesAPI() {
        CandidateListRequest request = CandidateListRequest.of().build();
        CandidateListResponse response = candidateService.getAPICandidates(request);
        return ResponseEntity.ok(JSONAssembler.toCandidateJsonList(response.getCandidatesAPI()));
    }
}
