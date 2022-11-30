package com.example.ase2022y203.candidateReferences.data;

import com.example.ase2022y203.candidateReferences.domain.CandidateReferences;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CandidateReferencesRepoJDBC extends CrudRepository<CandidateReferences, Integer> {
    Iterable<CandidateReferences> findAll();
    Optional<CandidateReferences> findByCid(Integer cid);
}
