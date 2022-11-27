package com.example.ase2022y203.register.service;

import com.example.ase2022y203.register.service.messages.SaveRegisterRequest;
import com.example.ase2022y203.register.service.messages.SaveRegistersResponse;
import com.example.ase2022y203.register.service.messages.SingleRegisterRequest;
import com.example.ase2022y203.register.service.messages.SingleRegisterResponse;

import java.util.Optional;

public interface RegisterService {
    SingleRegisterResponse getRegistersByRequest(SingleRegisterRequest singleRegisterRequest);
    SaveRegistersResponse process(SaveRegisterRequest newRegisterRequest);
    Optional<RegisterDTO> getRegistersID(Integer id);
}
