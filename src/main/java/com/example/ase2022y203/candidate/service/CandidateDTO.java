package com.example.ase2022y203.candidate.service;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class CandidateDTO {
    private Integer id;
    private String firstName;
    private String surname;
    private String email;
    private String password;
    private String companyName;
}
