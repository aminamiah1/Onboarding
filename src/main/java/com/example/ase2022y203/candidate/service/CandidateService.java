package com.example.ase2022y203.candidate.service;

import com.example.ase2022y203.candidate.domain.Candidate;
import com.example.ase2022y203.candidate.service.messages.CandidateListRequest;
import com.example.ase2022y203.candidate.service.messages.CandidateListResponse;

import java.util.Optional;

public interface CandidateService {
    public CandidateListResponse getCandidates(CandidateListRequest candidateListRequest);
    Optional<CandidateDTO> getCandidateByID(Integer id);
}
