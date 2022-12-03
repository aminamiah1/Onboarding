package com.example.ase2022y203.vettingOfficers.service;


import com.example.ase2022y203.vettingOfficers.domain.VettingOfficers;

public class VettingOfficersAssembler {
    static VettingOfficersDTO toDto(VettingOfficers v){
        return new VettingOfficersDTO(
                v.getId(), v.getFirst_name(), v.getSurname(), v.getEmail(), v.getPassword()
        );
    }
}
