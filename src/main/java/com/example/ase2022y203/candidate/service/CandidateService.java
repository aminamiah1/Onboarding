package com.example.ase2022y203.candidate.service;

import com.example.ase2022y203.candidate.domain.Candidate;
import com.example.ase2022y203.candidate.service.messages.*;

import java.util.Optional;

public interface CandidateService {
    CandidateListResponse getCandidates(CandidateListRequest candidateListRequest);
    Optional<CandidateDTO> getCandidateByID(Integer id);
    SingleCandidateResponse getCandidatesByRequest(SingleCandidateRequest singleCandidateRequest);
    SaveCandidateResponse process(SaveCandidateRequest newCandidateRequest);
    Optional<CandidateDTO> getCandidatesByCID(Integer cid);
}

