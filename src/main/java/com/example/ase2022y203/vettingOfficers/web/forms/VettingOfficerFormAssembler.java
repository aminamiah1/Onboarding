package com.example.ase2022y203.vettingOfficers.web.forms;

import com.example.ase2022y203.vettingOfficers.service.VettingOfficersDTO;

public class VettingOfficerFormAssembler {

    public static VettingOfficerForm toOfficerForm(VettingOfficersDTO officersDTO){
        return new VettingOfficerForm(officersDTO.getId(), officersDTO.getFirst_name(), officersDTO.getSurname(),
                officersDTO.getEmail(), officersDTO.getPassword());
    }
}
