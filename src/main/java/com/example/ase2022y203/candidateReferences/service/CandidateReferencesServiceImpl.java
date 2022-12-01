package com.example.ase2022y203.candidateReferences.service;

import com.example.ase2022y203.candidateReferences.data.CandidateReferencesRepository;
import com.example.ase2022y203.candidateReferences.domain.CandidateReferences;
import com.example.ase2022y203.candidateReferences.service.messages.CandidateRefListRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateReferencesServiceImpl implements CandidateReferencesService {

    private final CandidateReferencesRepository candidateReferencesRepository;

    public CandidateReferencesServiceImpl(CandidateReferencesRepository crRepo) {
        this.candidateReferencesRepository = crRepo;
    }

    @Override
    public List<CandidateReferencesDTO> getCandidateReferencesByCID(Integer cid) {
        List<CandidateReferences> candidateReferences;

        candidateReferences = candidateReferencesRepository.getCandidateReferencesByCID(cid);

        return candidateReferences.stream().map(cr -> CandidateReferencesAssembler.toDto(cr)).collect(Collectors.toList());
    }

}
