package com.example.ase2022y203.candidate.service;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class CandidateDTO {
    private Integer ID;
    private String First_Name;
    private String Surname;
    private String Email;
    private String Password;
    private String company_name;
}
