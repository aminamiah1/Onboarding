package com.example.ase2022y203.candidatePersonal.service;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class CandidatePersonalDTO {
    private Integer id;
    private Integer c_id;
    private String national_insurance;
    private String ethnicity;
    private String gender;
    private String age;
    private String sexuality;
}
