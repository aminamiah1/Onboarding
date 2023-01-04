package com.example.ase2022y203.candidate.api.json;

import com.example.ase2022y203.candidate.service.CandidateDTO;

import java.util.List;
import java.util.stream.Collectors;

public class JSONAssembler {
    public static JSON toCandidateJson(CandidateDTO candidateDTO) {
        return JSON
                .of()
                .id(candidateDTO.getId())
                .first_name(candidateDTO.getFirst_name())
                .surname(candidateDTO.getSurname())
                .email(candidateDTO.getEmail())
                .company_name(candidateDTO.getCompany_name())
                .build();
    }

    public static List<JSON> toCandidateJsonList(List<CandidateDTO> candidatesDTOList) {
        return candidatesDTOList
                .stream()
                .map(l -> JSONAssembler.toCandidateJson(l))
                .collect(Collectors.toList());
    }
}
