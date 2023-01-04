package com.example.ase2022y203.candidate.service;

import com.example.ase2022y203.candidate.domain.Candidate;
import com.example.ase2022y203.candidate.service.messages.*;

import java.util.List;
import java.util.Optional;

public interface CandidateService {
    CandidateListResponse getCandidates(CandidateListRequest candidateListRequest);

    CandidateListResponse getAllCandidates(CandidateListRequest candidateListRequest);
    Optional<CandidateDTO> getCandidateByID(Integer id);
    void addNewCandidate(CandidateDTOReg candidateDTO);
    Optional<CandidateDTO> getCandidateByEmail(String email);
    Optional<Candidate> getCandidateEntityByID(Integer id);
    CandidateListResponse getAPICandidates(CandidateListRequest candidateListRequest);
}

