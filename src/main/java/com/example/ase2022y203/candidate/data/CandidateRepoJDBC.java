package com.example.ase2022y203.candidate.data;

import com.example.ase2022y203.candidate.domain.Candidate;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CandidateRepoJDBC extends CrudRepository<Candidate, Integer> {
    Iterable<Candidate> findAll();
    Optional<Candidate> findByCid(Integer cid);
}
