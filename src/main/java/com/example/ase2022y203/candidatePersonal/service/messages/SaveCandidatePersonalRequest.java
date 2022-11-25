package com.example.ase2022y203.candidatePersonal.service.messages;

import com.example.ase2022y203.candidatePersonal.service.CandidatePersonalDTO;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderMethodName = "of")
public class SaveCandidatePersonalRequest {
    private final CandidatePersonalDTO candidatePersonalDTO;
}
