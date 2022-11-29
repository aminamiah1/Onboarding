package com.example.ase2022y203.candidate.service.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class RegistrationRequest {
    private String first_name;
    private String surname;
    private String email;
    private String password;
}
