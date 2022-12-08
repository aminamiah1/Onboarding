package com.example.ase2022y203.vettingOfficers.service;

import com.example.ase2022y203.vettingOfficers.domain.VettingOfficers;

import java.util.List;
import java.util.stream.Collectors;

public class VettingOfficersAssembler {

    static List<VettingOfficersDTO> toDto(List<VettingOfficers> officers) {
        return officers
                .stream()
                .map(c -> toDto(c))
                .collect(Collectors.toList());
    }

    static VettingOfficersDTO toDto(VettingOfficers v){
        return new VettingOfficersDTO(
                v.getId(), v.getFirst_name(), v.getSurname(), v.getEmail(), v.getPassword()
        );
    }
}
