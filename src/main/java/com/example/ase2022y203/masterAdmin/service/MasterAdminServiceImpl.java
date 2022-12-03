package com.example.ase2022y203.masterAdmin.service;

import com.example.ase2022y203.masterAdmin.data.MasterAdminRepository;
import com.example.ase2022y203.masterAdmin.domain.MasterAdmin;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MasterAdminServiceImpl implements  MasterAdminService {

    private final MasterAdminRepository masterAdminRepository;

    public MasterAdminServiceImpl(MasterAdminRepository repo) {
        this.masterAdminRepository = repo;
    }

    @Override
    public Optional<MasterAdminDTO> getMasterAdminByEmail(String email) {
        Optional<MasterAdmin> aMasterAdmin = masterAdminRepository.getMasterAdminByEmail(email);
        if(aMasterAdmin.isPresent()){
            System.out.println(aMasterAdmin.get());
            return Optional.of(MasterAdminAssembler.toDto(aMasterAdmin.get()));
        } else {
            return Optional.empty();
        }
    }
}
