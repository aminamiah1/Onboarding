package com.example.ase2022y203.candidate.data;

import com.example.ase2022y203.candidate.domain.Candidate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CandidateRepoJDBC extends CrudRepository<Candidate, Integer> {
    List<Candidate> findAll();
}
