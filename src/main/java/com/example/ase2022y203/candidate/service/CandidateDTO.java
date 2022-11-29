package com.example.ase2022y203.candidate.service;

import lombok.AllArgsConstructor;
import lombok.Value;

import javax.validation.constraints.Email;

@Value
@AllArgsConstructor
public class CandidateDTO {
    private Integer id;
    private String first_name;
    private String surname;
    @Email
    private String email;
    private String password;
    private String company_name;
}
