package com.example.ase2022y203.candidate.service;

import com.example.ase2022y203.candidate.domain.Candidate;

import java.util.List;
import java.util.stream.Collectors;

public class CandidateAssembler {
    static List<CandidateDTO> toDto(List<Candidate> candidates){
        return candidates
                .stream()
                .map(c -> toDto(c))
                .collect(Collectors.toList());
    }

    public static CandidateDTO toDto(Candidate c){
        return new CandidateDTO(c.getId(), c.getFirstName(), c.getSurname(),
                c.getEmail(), c.getPassword(), c.getCompanyName());
    }


}
