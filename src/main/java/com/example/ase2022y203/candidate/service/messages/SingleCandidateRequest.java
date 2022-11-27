package com.example.ase2022y203.candidate.service.messages;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotEmpty;

@Value
@Builder(builderMethodName = "of")
public class SingleCandidateRequest {
    @NotEmpty
    private final Integer id;
    private final Integer cid;
}

