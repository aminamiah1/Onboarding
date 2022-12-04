package com.example.ase2022y203.candidateReferences.service;

import com.example.ase2022y203.candidateReferences.domain.CandidateReferences;

import java.util.List;
import java.util.stream.Collectors;

public class CandidateReferencesAssembler {
    public static CandidateReferencesDTO toDto(CandidateReferences r){
        return new CandidateReferencesDTO(r.getId(), r.getCid(), r.getReferee_name(), r.getReferee_phone_number());
    }
}
