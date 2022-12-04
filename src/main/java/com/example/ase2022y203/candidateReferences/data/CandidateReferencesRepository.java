package com.example.ase2022y203.candidateReferences.data;

import com.example.ase2022y203.candidateReferences.domain.CandidateReferences;
import com.example.ase2022y203.candidateReferences.service.messages.CandidateRefListRequest;

import java.util.List;


public interface CandidateReferencesRepository{
    List<CandidateReferences> getCandidateReferencesByCID(Integer cid);
    void save(CandidateReferences newReference);
    void update(CandidateReferences updatedReference);
    void delete(CandidateReferences deleteReference);
    List<CandidateReferences> getReferences();
}
