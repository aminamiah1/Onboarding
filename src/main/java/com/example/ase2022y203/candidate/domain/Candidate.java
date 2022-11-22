package com.example.ase2022y203.candidate.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Candidate Data Class For Mapping Candidate To Database
 */
@Data
@AllArgsConstructor
public class Candidate {
    private Integer id;
    private String firstName;
    private String surname;
    private String email;
    private String password;
    private String companyName;
}
