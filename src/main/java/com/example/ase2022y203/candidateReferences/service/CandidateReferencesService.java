package com.example.ase2022y203.candidateReferences.service;

import com.example.ase2022y203.candidateReferences.service.messages.CandidateRefListRequest;

import java.util.List;

public interface CandidateReferencesService {
    List<CandidateReferencesDTO> getCandidateReferencesByCID(Integer cid);
}
