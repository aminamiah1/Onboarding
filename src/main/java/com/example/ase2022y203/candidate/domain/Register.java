package com.example.ase2022y203.candidate.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Register {
    private Integer ID;
    @NotEmpty(message = "{name.invalid}")
    private String first_name;
    @NotEmpty(message = "{name.invalid}")
    private String surname;
    @NotEmpty(message = "{email.invalid}")
    private String email;
    @NotEmpty(message = "{password.invalid}")
    private String password;
    @NotEmpty(message = "{company.invalid}")
    private String company_Name;
}

