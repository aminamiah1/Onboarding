package com.example.ase2022y203.candidatePersonal.service;

import com.example.ase2022y203.candidatePersonal.domain.PersonalInformation;

public class CandidatePersonalAssembler {
    public static CandidatePersonalDTO toDto(PersonalInformation cp){
        return new CandidatePersonalDTO(cp.getId(), cp.getCid(), cp.getNational_insurance(),
                cp.getEthnicity(), cp.getGender(), cp.getAge(), cp.getTelephone_number());
    }
}
