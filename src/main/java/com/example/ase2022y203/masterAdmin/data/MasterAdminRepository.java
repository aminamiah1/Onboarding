package com.example.ase2022y203.masterAdmin.data;

import com.example.ase2022y203.masterAdmin.domain.MasterAdmin;

import java.util.Optional;

public interface MasterAdminRepository {
    Optional<MasterAdmin> getMasterAdminByEmail(String email);
}
