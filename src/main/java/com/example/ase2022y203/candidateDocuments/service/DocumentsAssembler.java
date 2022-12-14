package com.example.ase2022y203.candidateDocuments.service;

import com.example.ase2022y203.candidateDocuments.domain.Documents;

import java.util.List;
import java.util.stream.Collectors;

public class DocumentsAssembler {
    static List<DocumentsDTO> toDto(List<Documents> documents){
        return documents
                .stream()
                .map(d -> toDto(d))
                .collect(Collectors.toList());
    }

    public static DocumentsDTO toDto(Documents d){
        return new DocumentsDTO(d.getId(), d.getCid(), d.getDocumentName(), d.getDocumentType(), d.getDocumentStatus());
    }
}
