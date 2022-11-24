package com.example.ase2022y203.candidatePersonal.data;

import com.example.ase2022y203.candidatePersonal.domain.CandidatePersonal;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CandidatePersonalRepository{
        Optional<CandidatePersonal> getCandidatePersonalInfoByCID(Integer cid);
}
