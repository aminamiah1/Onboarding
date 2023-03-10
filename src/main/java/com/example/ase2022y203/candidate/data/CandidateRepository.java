package com.example.ase2022y203.candidate.data;

import com.example.ase2022y203.candidate.domain.Candidate;

import java.util.List;
import java.util.Optional;


public interface CandidateRepository {
    List<Candidate> getCandidates();
    List<Candidate> getAllCandidates();
    Optional<Candidate> getCandidateByID(Integer id);
    Optional<Candidate> getCandidateByEmail(String email);
    void save(Candidate newCandidate);
}
