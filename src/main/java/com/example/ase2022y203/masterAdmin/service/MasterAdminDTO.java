package com.example.ase2022y203.masterAdmin.service;

import lombok.AllArgsConstructor;
import lombok.Value;

import javax.validation.constraints.Email;

@Value
@AllArgsConstructor
public class MasterAdminDTO {
    private Integer id;
    @Email
    private String email;
    private String password;
}
