package com.example.ase2022y203.candidateReferences.data;

import com.example.ase2022y203.candidateReferences.domain.CandidateReferences;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CandidateReferencesRepositoryImpl implements CandidateReferencesRepository {

    private CandidateReferencesRepoJDBC candidateRefRepoJDBC;

    private final JdbcTemplate jdbc;

    public CandidateReferencesRepositoryImpl(CandidateReferencesRepoJDBC aRepo, JdbcTemplate jdbc){
        candidateRefRepoJDBC =  aRepo;
        this.jdbc = jdbc;
    }

    @Override
    public List<CandidateReferences> getCandidateReferencesByCID(Integer cid) {
        return candidateRefRepoJDBC.findAllByCid(cid);
    }

    @Override
    public void save(CandidateReferences newReference) {
       String addNewReferenceSQL = "INSERT INTO Candidate_References (cid, referee_name, referee_phone_number) values(?, ?, ?)";
        jdbc.update(addNewReferenceSQL, newReference.getCid(), newReference.getReferee_name(), newReference.getReferee_phone_number());
    }

    @Override
    public void update(CandidateReferences updatedReference) {
        candidateRefRepoJDBC.save(updatedReference);
    }

    @Override
    public void delete(CandidateReferences deleteReference) {
        candidateRefRepoJDBC.delete(deleteReference);
    }

    @Override
    public List<CandidateReferences> getReferences() {
        return candidateRefRepoJDBC.findAll();
    }


}
