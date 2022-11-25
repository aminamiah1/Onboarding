package com.example.ase2022y203.register.service;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.data.relational.core.mapping.MappedCollection;

@Value
@AllArgsConstructor
public class RegisterDTO {
    private Integer ID;
    private String first_name;
    private String surname;
    private String email;
    private String password;
}
