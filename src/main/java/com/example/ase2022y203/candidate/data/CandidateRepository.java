package com.example.ase2022y203.candidate.data;
import com.example.ase2022y203.candidate.domain.Candidate;

import java.util.List;


public interface CandidateRepository {
    List<Candidate> getCandidates();
}
