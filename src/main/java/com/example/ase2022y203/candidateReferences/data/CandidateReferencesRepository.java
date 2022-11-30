package com.example.ase2022y203.candidateReferences.data;

import com.example.ase2022y203.candidateReferences.domain.CandidateReferences;

import java.util.Optional;

public interface CandidateReferencesRepository{
    Optional<CandidateReferences> getCandidateReferencesByCID(Integer cid);
}
