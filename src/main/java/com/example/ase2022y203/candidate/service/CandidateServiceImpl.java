package com.example.ase2022y203.candidate.service;

import com.example.ase2022y203.candidate.data.CandidateRepository;
import com.example.ase2022y203.candidate.domain.Candidate;
import com.example.ase2022y203.candidate.service.messages.CandidateListRequest;
import com.example.ase2022y203.candidate.service.messages.CandidateListResponse;

import java.util.List;

public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository candidateRepository;

    public CandidateServiceImpl(CandidateRepository repo) {
        this.candidateRepository = repo;
    }

    private List<CandidateDTO> getCandidates() {
        List<Candidate> candidates = candidateRepository.getCandidates();
        return CandidateAssembler.toDto(candidates);
    }

    public CandidateListResponse getCandidates(CandidateListRequest candidateListRequest) {
        List<CandidateDTO> candidates;
        candidates = getCandidates();
        return CandidateListResponse.of()
                .request(candidateListRequest)
                .candidates(candidates)
                .build();
    }
}
