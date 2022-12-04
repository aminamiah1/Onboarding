package com.example.ase2022y203.masterAdmin.service;


import java.util.Optional;

public interface MasterAdminService {
    Optional<MasterAdminDTO> getMasterAdminByEmail(String email);
}
