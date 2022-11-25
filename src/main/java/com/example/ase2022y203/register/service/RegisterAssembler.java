package com.example.ase2022y203.register.service;

import com.example.ase2022y203.register.domain.Register;
import com.example.ase2022y203.register.service.RegisterDTO;

public class RegisterAssembler {
    public static RegisterDTO toDto(Register register){
        return new RegisterDTO(register.getID(), register.getFirst_name(),
                register.getSurname(), register.getEmail(), register.getPassword());
    }
}
