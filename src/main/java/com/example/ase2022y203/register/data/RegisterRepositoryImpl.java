package com.example.ase2022y203.register.data;

import com.example.ase2022y203.candidate.domain.Candidate;
import com.example.ase2022y203.register.domain.Register;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RegisterRepositoryImpl implements RegisterRepository{

    private RegisterRepoJDBC registerRepoJDBC;
    public RegisterRepositoryImpl(RegisterRepoJDBC repoJDBC){
        registerRepoJDBC = repoJDBC;
    }

    @Override
    public List<Candidate> getCandidates() {
        return null;
    }

    @Override
    public void save(Register register){
        registerRepoJDBC.save(register);
    }

}
