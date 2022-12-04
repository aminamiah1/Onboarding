package com.example.ase2022y203.vettingOfficers.service;

import java.util.Optional;

public interface VettingOfficersService {
    Optional<VettingOfficersDTO> getVettingOfficerByEmail(String email);
}
