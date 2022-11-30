package com.example.ase2022y203.candidateReferences.data;

import com.example.ase2022y203.candidate.data.CandidateRepositoryImpl;
import com.example.ase2022y203.candidateReferences.domain.CandidateReferences;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CandidateReferencesRepositoryImpl implements CandidateReferencesRepository {

    private CandidateReferencesRepoJDBC candidateRefRepoJDBC;

    public CandidateReferencesRepositoryImpl(CandidateReferencesRepoJDBC aRepo){
        candidateRefRepoJDBC =  aRepo;
    }

    @Override
    public List<CandidateReferences> getCandidateReferencesByCID(Integer cid) {
        return candidateRefRepoJDBC.findAllByCid(cid);
    }

}
