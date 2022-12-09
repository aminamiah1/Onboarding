package com.example.ase2022y203.applications.service;

import com.example.ase2022y203.applications.domain.Applications;

import java.util.List;
import java.util.stream.Collectors;

public class ApplicationsAssembler {
    static List<ApplicationsDTO> toDto(List<Applications> applications){
        return applications
                .stream()
                .map(a -> toDto(a))
                .collect(Collectors.toList());
    }

    public static ApplicationsDTO toDto(Applications a){
        return new ApplicationsDTO(a.getId(), a.getAppstatus(), a.getCid());
    }

}
