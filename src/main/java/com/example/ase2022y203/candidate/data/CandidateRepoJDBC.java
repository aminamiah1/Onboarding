package com.example.ase2022y203.candidate.data;

import com.example.ase2022y203.candidate.domain.Candidate;
import org.springframework.data.repository.CrudRepository;

public interface CandidateRepoJDBC extends CrudRepository<Candidate, Integer> {
    Iterable<Candidate> findAll();
}
