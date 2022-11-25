package com.example.ase2022y203.candidate.service;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class CandidateDTO {
    private Integer ID;
    private String first_name;
    private String surname;
    private String email;
    private String password;
    private String company_name;
}
