package com.example.ase2022y203.candidateReferences.service.messages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.Optional;

@Value
@AllArgsConstructor
@Builder(builderMethodName = "of")
public class CandidateRefListRequest {
    private Optional<String> searchTerm;
    private List<String> orderBy;
    private Integer limit;
}
