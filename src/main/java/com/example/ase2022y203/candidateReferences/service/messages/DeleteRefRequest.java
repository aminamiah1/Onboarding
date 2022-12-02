package com.example.ase2022y203.candidateReferences.service.messages;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderMethodName = "of")
public class DeleteRefRequest {
    private Integer referenceID;
}
