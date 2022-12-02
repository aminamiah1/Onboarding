package com.example.ase2022y203.candidate.service;

import com.example.ase2022y203.candidate.domain.Candidate;

import java.util.List;
import java.util.stream.Collectors;

public class CandidateAssembler {
    static List<CandidateDTO> toDto(List<Candidate> candidates) {
        return candidates
                .stream()
                .map(c -> toDto(c))
                .collect(Collectors.toList());
    }

    public static CandidateDTO toDto(Candidate c) {
        return new CandidateDTO(c.getId(), c.getFirst_name(), c.getSurname(),
                c.getEmail(), c.getPassword(), c.getCompany_name());
    }

    static List<CandidateDTOReg> toDTO(List<Candidate> candidates) {
        return candidates
                .stream()
                .map(b -> toDTO(b))
                .collect(Collectors.toList());
    }
    public static CandidateDTOReg toDTO(Candidate b){
        return new CandidateDTOReg(b.getFirst_name(), b.getSurname(),
                b.getEmail(), b.getPassword(), b.getCompany_name());
    }
}
