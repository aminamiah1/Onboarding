package com.example.ase2022y203.register.service.messages;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotEmpty;

@Value
@Builder(builderMethodName = "of")
public class SingleRegisterRequest {
    @NotEmpty
    private final Integer id;
}

