package com.example.ase2022y203.vettingOfficers.service;

import lombok.AllArgsConstructor;
import lombok.Value;

import javax.validation.constraints.Email;

@Value
@AllArgsConstructor
public class VettingOfficersDTO {
    private Integer id;
    private String first_name;
    private String surname;
    @Email
    private String email;
    private String password;
}
