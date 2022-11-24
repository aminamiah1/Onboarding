package com.example.ase2022y203.candidatePersonal.service;

import com.example.ase2022y203.candidatePersonal.domain.CandidatePersonal;

public class CandidatePersonalAssembler {
    public static CandidatePersonalDTO toDto(CandidatePersonal cp){
        return new CandidatePersonalDTO(cp.getId(), cp.getC_id(), cp.getNational_insurance(),
                cp.getEthnicity(), cp.getGender(), cp.getAge(), cp.getSexuality());
    }
}
