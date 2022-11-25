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
    private String First_Name;
    @NotEmpty(message = "{name.invalid}")
    private String Surname;
    @NotEmpty(message = "{email.invalid}")
    private String Email;
    @NotEmpty(message = "{password.invalid}")
    private String Password;
    @NotEmpty(message = "{company.invalid}")
    private String Company_Name;
}

