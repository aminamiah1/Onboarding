package com.example.ase2022y203.candidate.service.messages;

import com.example.ase2022y203.candidate.service.CandidateDTO;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderMethodName = "of")
public class SaveCandidateRequest {
    private final CandidateDTO candidateDTO;
}
