package com.example.ase2022y203.candidate.service.messages;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderMethodName = "of")
public class SaveCandidateResponse {
    private final SaveCandidateRequest request;
}
