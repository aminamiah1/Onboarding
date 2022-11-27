package com.example.ase2022y203.candidate.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Table;

@Data
@AllArgsConstructor
@Table
public class Candidate {
    private Integer id;
    private String first_name;
    private String surname;
    private String email;
    private String password;
    private String company_name;
}

