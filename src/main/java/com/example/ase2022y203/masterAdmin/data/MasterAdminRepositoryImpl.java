package com.example.ase2022y203.masterAdmin.data;

import com.example.ase2022y203.candidate.domain.Candidate;
import com.example.ase2022y203.masterAdmin.domain.MasterAdmin;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MasterAdminRepositoryImpl implements MasterAdminRepository {

    private MasterAdminRepoJDBC masterAdminRepoJDBC;

    public MasterAdminRepositoryImpl(MasterAdminRepoJDBC aRepo, JdbcTemplate jdbcTemplate){
        masterAdminRepoJDBC = aRepo;
    }

    @Override
    public Optional<MasterAdmin> getMasterAdminByEmail(String email) {
        return masterAdminRepoJDBC.findMasterAdminByEmail(email);
    }
}
