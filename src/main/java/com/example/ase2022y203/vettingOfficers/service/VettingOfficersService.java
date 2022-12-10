package com.example.ase2022y203.vettingOfficers.service;

import com.example.ase2022y203.vettingOfficers.domain.VettingOfficers;
import com.example.ase2022y203.vettingOfficers.service.messages.OfficersListRequest;
import com.example.ase2022y203.vettingOfficers.service.messages.OfficersListResponse;

import java.util.Optional;

public interface VettingOfficersService {

    OfficersListResponse getOfficers(OfficersListRequest officersListRequest);

    Optional<VettingOfficersDTO> getVettingOfficerByEmail(String email);

    Optional<VettingOfficersDTO> getVettingOfficerById(Optional<Integer> id);

    void addNewAdmin(VettingOfficersDTO officerDTO);

    void delete(VettingOfficersDTO vettingOfficersDTO);
}
