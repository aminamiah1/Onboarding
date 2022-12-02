package com.example.ase2022y203.vettingOfficers.data;

import com.example.ase2022y203.masterAdmin.data.MasterAdminRepoJDBC;
import com.example.ase2022y203.masterAdmin.domain.MasterAdmin;
import com.example.ase2022y203.vettingOfficers.domain.VettingOfficers;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VetOfficerRepositoryImpl implements VetOfficerRepository {
    private VetOfficerRepoJDBC vetOfficerRepoJDBC;

    public VetOfficerRepositoryImpl(VetOfficerRepoJDBC aRepo){
        vetOfficerRepoJDBC = aRepo;
    }

    @Override
    public Optional<VettingOfficers> getVettingOfficerByEmail(String email) {
        return vetOfficerRepoJDBC.findVettingOfficersByEmail(email);
    }
}
