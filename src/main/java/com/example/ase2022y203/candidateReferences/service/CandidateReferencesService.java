package com.example.ase2022y203.candidateReferences.service;

import com.example.ase2022y203.candidateReferences.domain.CandidateReferences;

import java.util.List;

public interface CandidateReferencesService {
    List<CandidateReferencesDTO> getCandidateReferencesByCID(Integer cid);
    void addNewReference(CandidateReferencesDTOSave referenceDTO);
    void updateReference(CandidateReferencesDTO referenceDTO);
    void deleteReference(CandidateReferencesDTO candidateReferencesDTO);
    List<CandidateReferences> getAllReferences();
}
