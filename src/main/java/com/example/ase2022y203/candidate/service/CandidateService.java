package com.example.ase2022y203.candidate.service;

import com.example.ase2022y203.candidate.service.messages.*;

import java.util.Optional;

public interface CandidateService {
    CandidateListResponse getCandidates(CandidateListRequest candidateListRequest);
    Optional<CandidateDTO> getCandidateByID(Integer id);
    void addNewCandidate(CandidateDTO candidateDTO);
}

