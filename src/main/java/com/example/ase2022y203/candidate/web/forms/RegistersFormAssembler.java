package com.example.ase2022y203.candidate.web.forms;

import com.example.ase2022y203.candidate.service.CandidateDTO;

public class RegistersFormAssembler {
    public static RegistersForm toRegistersForm(CandidateDTO candidateDTO){
        return new RegistersForm(candidateDTO.getId(), candidateDTO.getFirst_name(),
                candidateDTO.getSurname(), candidateDTO.getEmail(), candidateDTO.getPassword(), candidateDTO.getCompany_name());
    }
}
