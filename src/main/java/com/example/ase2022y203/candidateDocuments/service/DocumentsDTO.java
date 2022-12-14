package com.example.ase2022y203.candidateDocuments.service;

import com.example.ase2022y203.candidate.domain.Candidate;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class DocumentsDTO {
    private Integer id;
    private Candidate cid;
    private String documentName;
    private String documentType;
    private String documentStatus;
}
