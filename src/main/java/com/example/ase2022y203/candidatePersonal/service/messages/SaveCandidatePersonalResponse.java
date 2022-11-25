package com.example.ase2022y203.candidatePersonal.service.messages;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderMethodName = "of")
public class SaveCandidatePersonalResponse {
    private final SaveCandidatePersonalRequest request;
}
