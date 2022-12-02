package com.example.ase2022y203.vettingOfficers.data;

import com.example.ase2022y203.masterAdmin.domain.MasterAdmin;
import com.example.ase2022y203.vettingOfficers.domain.VettingOfficers;

import java.util.Optional;

public interface VetOfficerRepository {
    Optional<VettingOfficers> getVettingOfficerByEmail(String email);
}
