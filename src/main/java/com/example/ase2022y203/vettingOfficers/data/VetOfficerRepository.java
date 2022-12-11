package com.example.ase2022y203.vettingOfficers.data;

import com.example.ase2022y203.vettingOfficers.domain.VettingOfficers;

import java.util.List;
import java.util.Optional;

public interface VetOfficerRepository {
    Optional<VettingOfficers> getVettingOfficerByEmail(String email);
    Optional<VettingOfficers> getVettingOfficerById(Optional<Integer> id);
    List<VettingOfficers> getOfficers();
    void save(VettingOfficers newAdmin);
    void delete(VettingOfficers vettingOfficers);
    void update(VettingOfficers updateVettingOfficers);
}
