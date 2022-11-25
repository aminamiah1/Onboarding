package com.example.ase2022y203.register.service.messages;

import com.example.ase2022y203.register.service.RegisterDTO;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderMethodName = "of")
public class SaveRegisterRequest {
    private final RegisterDTO registerDTO;
}
