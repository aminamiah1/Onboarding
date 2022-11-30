package com.example.ase2022y203.candidateReferences.service;

import com.example.ase2022y203.candidateReferences.domain.CandidateReferences;

public class CandidateReferencesAssembler {
    public static CandidateReferencesDTO toDto(CandidateReferences cr){
        return new CandidateReferencesDTO(cr.getId(), cr.getCid(), cr.getReferee_name(), cr.getReferee_phone_number());
    }
}
