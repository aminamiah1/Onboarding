package com.example.ase2022y203.masterAdmin.data;

import com.example.ase2022y203.masterAdmin.domain.MasterAdmin;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MasterAdminRepoJDBC extends CrudRepository<MasterAdmin, Integer> {
    Optional<MasterAdmin> findMasterAdminByEmail(String email);
}
