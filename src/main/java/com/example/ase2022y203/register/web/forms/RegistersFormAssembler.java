package com.example.ase2022y203.register.web.forms;

import com.example.ase2022y203.register.service.RegisterDTO;

public class RegistersFormAssembler {
    public static RegistersForm toRegistersForm(RegisterDTO registerDTO){
        return new RegistersForm(registerDTO.getID(), registerDTO.getFirst_name(),
                registerDTO.getSurname(), registerDTO.getEmail(), registerDTO.getPassword());
    }
}
