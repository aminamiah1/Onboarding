package com.example.ase2022y203.vettingOfficers.data;

import com.example.ase2022y203.vettingOfficers.domain.VettingOfficers;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface VetOfficerRepoJDBC extends CrudRepository<VettingOfficers, Integer> {
    Optional<VettingOfficers> findVettingOfficersByEmail(String email);

    Optional<VettingOfficers> findVettingOfficersById(Optional<Integer> id);

    @Override
    void delete(VettingOfficers vettingOfficers);
}
