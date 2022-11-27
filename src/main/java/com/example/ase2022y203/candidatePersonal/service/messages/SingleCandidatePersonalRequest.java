package com.example.ase2022y203.candidatePersonal.service.messages;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotEmpty;

@Value
@Builder(builderMethodName = "of")
public class SingleCandidatePersonalRequest {
    @NotEmpty
    private final Integer id;
    private final Integer cid;
}
