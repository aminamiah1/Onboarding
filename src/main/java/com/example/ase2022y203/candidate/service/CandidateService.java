package com.example.ase2022y203.candidate.service;

import com.example.ase2022y203.candidate.service.messages.CandidateListRequest;
import com.example.ase2022y203.candidate.service.messages.CandidateListResponse;

public interface CandidateService {
    public CandidateListResponse getCandidates(CandidateListRequest candidateListRequest);
}
