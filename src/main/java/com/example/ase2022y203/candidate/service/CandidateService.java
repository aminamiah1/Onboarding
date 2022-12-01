package com.example.ase2022y203.candidate.service;

import com.example.ase2022y203.candidate.service.messages.*;

import java.util.List;
import java.util.Optional;

public interface CandidateService {
    CandidateListResponse getCandidates(CandidateListRequest candidateListRequest);

    List<CandidateDTO> getAllCandidates();
    Optional<CandidateDTO> getCandidateByID(Integer id);
    void addNewCandidate(CandidateDTOReg candidateDTO);
    Optional<CandidateDTO> getCandidateByEmail(String email);
}

