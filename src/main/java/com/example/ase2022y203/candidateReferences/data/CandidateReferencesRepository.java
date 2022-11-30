package com.example.ase2022y203.candidateReferences.data;

import com.example.ase2022y203.candidateReferences.domain.CandidateReferences;

import java.util.List;


public interface CandidateReferencesRepository{
    List<CandidateReferences> getCandidateReferencesByCID(Integer cid);
}
