package com.example.ase2022y203.candidatePersonal.data;

import com.example.ase2022y203.candidatePersonal.domain.CandidatePersonal;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CandidatePersonalRepoJDBC extends CrudRepository<CandidatePersonal, Integer> {
    Iterable<CandidatePersonal> findAll();
    Optional<CandidatePersonal> findByC_id(Integer cid);

}
