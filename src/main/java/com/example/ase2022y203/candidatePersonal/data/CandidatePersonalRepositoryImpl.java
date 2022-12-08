package com.example.ase2022y203.candidatePersonal.data;

import com.example.ase2022y203.candidate.domain.Candidate;
import com.example.ase2022y203.candidate.service.CandidateAssembler;
import com.example.ase2022y203.candidate.service.CandidateDTO;
import com.example.ase2022y203.candidatePersonal.domain.PersonalInformation;
import com.example.ase2022y203.candidatePersonal.service.CandidatePersonalAssembler;
import com.example.ase2022y203.candidatePersonal.service.CandidatePersonalDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CandidatePersonalRepositoryImpl implements CandidatePersonalRepository {

    private CandidatePersonalRepoJDBC candidatePersonalJdbcRepo;
    public CandidatePersonalRepositoryImpl(CandidatePersonalRepoJDBC aRepo){
        candidatePersonalJdbcRepo = aRepo;
    }
    @Override
    public Optional<PersonalInformation> getCandidatePersonalInfoByCID(Integer cid) {
        return candidatePersonalJdbcRepo.findByCid(cid);
    }
    @Override
    public void save(PersonalInformation personalInformation){
        candidatePersonalJdbcRepo.save(personalInformation);
    }

    @Override
    public List<PersonalInformation> findAll() {
        return candidatePersonalJdbcRepo.findAll();
    }

}
