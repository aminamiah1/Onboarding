package com.example.ase2022y203.candidatePersonal.web.forms;

import com.example.ase2022y203.candidatePersonal.service.CandidatePersonalDTO;

public class CandidatePersonalFormAssembler {
    public static CandidatePersonalForm toCandidatePersonalForm(CandidatePersonalDTO candidatePersonalDTO) {
        return new CandidatePersonalForm(candidatePersonalDTO.getId(),candidatePersonalDTO.getC_id(), candidatePersonalDTO.getAge(),
                candidatePersonalDTO.getGender(), candidatePersonalDTO.getEthnicity(),
                candidatePersonalDTO.getNational_insurance(), candidatePersonalDTO.getSexuality());
    }
}
