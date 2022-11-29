package com.example.ase2022y203.candidatePersonal.data;

import com.example.ase2022y203.candidatePersonal.domain.PersonalInformation;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CandidatePersonalRepoJDBC extends CrudRepository<PersonalInformation, Integer> {
    Iterable<PersonalInformation> findAll();
    Optional<PersonalInformation> findByCid(Integer cid);

}
