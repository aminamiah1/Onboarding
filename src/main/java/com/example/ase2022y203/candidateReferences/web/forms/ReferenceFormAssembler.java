package com.example.ase2022y203.candidateReferences.web.forms;

import com.example.ase2022y203.candidateReferences.service.CandidateReferencesDTO;

public class ReferenceFormAssembler {
    public static ReferenceForm toReferenceForm(CandidateReferencesDTO candidateReferencesDTO){
        return new ReferenceForm(candidateReferencesDTO.getId(), candidateReferencesDTO.getC_id(),
                candidateReferencesDTO.getReferee_name(), candidateReferencesDTO.getReferee_phone_number());
    }
}
