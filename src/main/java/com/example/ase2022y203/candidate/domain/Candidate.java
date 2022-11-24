package com.example.ase2022y203.candidate.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Candidate {
    private Integer id;
    private String first_name;
    private String surname;
    private String email;
    private String password;
    private String company_name;

    public Candidate(Integer id, String first_name, String surname, String email, String password) {
        this.id = id;
        this.first_name = first_name;
        this.surname = surname;
        this.email = email;
        this.password = password;

    }
}
