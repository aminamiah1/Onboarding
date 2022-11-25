package com.example.ase2022y203.register.service.messages;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderMethodName = "of")
public class SaveRegistersResponse {
    private final SaveRegisterRequest request;
}
