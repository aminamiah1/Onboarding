package com.example.ase2022y203.register.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Register {
    @Id
    private Integer ID;
    private String first_name;
    private String surname;
    private String email;
    private String password;
}


