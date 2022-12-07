package com.example.ase2022y203.candidatePersonal.data;

import com.example.ase2022y203.candidatePersonal.domain.PersonalInformation;

import java.util.List;
import java.util.Optional;

public interface CandidatePersonalRepository{
        Optional<PersonalInformation> getCandidatePersonalInfoByCID(Integer cid);

        void save(PersonalInformation newPersonalInformation);

        List<PersonalInformation> findAll();
}
