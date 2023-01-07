package com.example.ase2022y203.candidate.api.json;

import com.example.ase2022y203.candidate.service.CandidateDTOReg;

import java.util.List;
import java.util.stream.Collectors;

public class JSONAssembler {
    public static CandidateJSON toCandidateJson(CandidateDTOReg candidateDTO) {
        return CandidateJSON
                .of()
                .first_name(candidateDTO.getFirst_name())
                .surname(candidateDTO.getSurname())
                .email(candidateDTO.getEmail())
                .company_name(candidateDTO.getCompany_name())
                .build();
    }

    public static List<CandidateJSON> toCandidateJsonList(List<CandidateDTOReg> candidatesDTOList) {
        return candidatesDTOList
                .stream()
                .map(l -> JSONAssembler.toCandidateJson(l))
                .collect(Collectors.toList());
    }
}

