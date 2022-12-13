package com.example.ase2022y203.candidateDocuments.service;

import com.example.ase2022y203.candidate.domain.Candidate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DocumentsDTOSave {
    private Candidate cid;
    private String documentName;
    private String documentType;
    private String documentStatus;
}
