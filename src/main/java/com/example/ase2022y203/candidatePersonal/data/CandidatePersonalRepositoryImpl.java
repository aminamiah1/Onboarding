package com.example.ase2022y203.candidatePersonal.data;

import com.example.ase2022y203.candidatePersonal.domain.PersonalInformation;
import org.springframework.stereotype.Repository;

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

}
