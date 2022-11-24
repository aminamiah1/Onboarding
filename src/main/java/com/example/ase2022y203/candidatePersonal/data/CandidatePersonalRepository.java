package com.example.ase2022y203.candidatePersonal.data;

import com.example.ase2022y203.candidatePersonal.domain.PersonalInformation;

import java.util.Optional;

public interface CandidatePersonalRepository{
        Optional<PersonalInformation> getCandidatePersonalInfoByCID(Integer cid);
}
