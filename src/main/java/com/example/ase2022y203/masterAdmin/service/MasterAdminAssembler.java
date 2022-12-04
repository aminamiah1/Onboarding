package com.example.ase2022y203.masterAdmin.service;


import com.example.ase2022y203.masterAdmin.domain.MasterAdmin;

public class MasterAdminAssembler {
    static MasterAdminDTO toDto(MasterAdmin a){
        return new MasterAdminDTO(
                a.getId(), a.getEmail(), a.getPassword()
        );
    }
}
