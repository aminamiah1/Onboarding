package com.example.ase2022y203.candidatePersonal.service;

import com.example.ase2022y203.candidate.domain.Candidate;
import com.example.ase2022y203.candidate.service.CandidateDTO;
import com.example.ase2022y203.candidatePersonal.domain.PersonalInformation;

import java.util.List;
import java.util.stream.Collectors;

public class CandidatePersonalAssembler {
    public static CandidatePersonalDTO toDto(PersonalInformation cp){
        return new CandidatePersonalDTO(cp.getId(), cp.getCid(), cp.getNational_insurance(),
                cp.getEthnicity(), cp.getGender(), cp.getAge(), cp.getTelephone_number());
    }

    static List<CandidatePersonalDTO> toDto(List<PersonalInformation> personalInformation) {
        return personalInformation
                .stream()
                .map(p -> toDto(p))
                .collect(Collectors.toList());
    }
}
