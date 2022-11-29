package com.example.ase2022y203.candidate.service;

import com.example.ase2022y203.candidate.data.CandidateRepository;
import com.example.ase2022y203.candidate.domain.Candidate;
import com.example.ase2022y203.candidate.service.messages.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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


    @Override
    public Optional<CandidateDTO> getCandidateByID(Integer id) {
        Optional<Candidate> aCandidate = candidateRepository.getCandidateByID(id);
        if (aCandidate.isPresent()) {
            System.out.println(aCandidate.get());
            return Optional.of(CandidateAssembler.toDto(aCandidate.get()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void addNewCandidate(CandidateDTOReg candidateDTO) {
        Candidate newCandidate = new Candidate(
                candidateRepository.getCandidates()
                        .get(candidateRepository.getCandidates().size() - 1)
                        .getId() + 1,
                candidateDTO.getFirst_name(),
                candidateDTO.getSurname(),
                candidateDTO.getEmail(),
                candidateDTO.getPassword(),
                candidateDTO.getCompany_name()
        );
        candidateRepository.save(newCandidate);
    }


    @Override
    public Optional<CandidateDTO> getCandidateByEmail(String email) {
        Optional<Candidate> aCandidate = candidateRepository.getCandidateByEmail(email);
        if (aCandidate.isPresent()) {
            System.out.println(aCandidate.get());
            return Optional.of(CandidateAssembler.toDto(aCandidate.get()));
        } else {
            return Optional.empty();
        }
    }

}



