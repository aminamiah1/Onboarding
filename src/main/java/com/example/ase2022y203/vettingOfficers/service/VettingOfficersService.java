package com.example.ase2022y203.vettingOfficers.service;

import com.example.ase2022y203.vettingOfficers.service.messages.OfficersListRequest;
import com.example.ase2022y203.vettingOfficers.service.messages.OfficersListResponse;

import java.util.Optional;

public interface VettingOfficersService {

    OfficersListResponse getOfficers(OfficersListRequest officersListRequest);
    Optional<VettingOfficersDTO> getVettingOfficerByEmail(String email);

    void addNewAdmin(VettingOfficersDTO officerDTO);
}
