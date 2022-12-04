package com.example.ase2022y203.candidateReferences.service.messages;

import com.example.ase2022y203.candidateReferences.service.CandidateReferencesDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor
@Builder(builderMethodName = "of")
public class CandidateRefListResponse {
    private CandidateRefListRequest request;
    private List<CandidateReferencesDTO> candidateReferences;
}
