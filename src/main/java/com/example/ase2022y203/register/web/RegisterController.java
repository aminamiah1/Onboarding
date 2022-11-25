package com.example.ase2022y203.register.web;

import com.example.ase2022y203.candidate.service.CandidateService;
import com.example.ase2022y203.register.service.RegisterService;

public class RegisterController {
    private final RegisterService registerService;
    private final CandidateService candidateService;

    public RegisterController(RegisterService svc, CandidateService candidateService){
        this.registerService = svc;
        this.candidateService = candidateService;
    }
}
