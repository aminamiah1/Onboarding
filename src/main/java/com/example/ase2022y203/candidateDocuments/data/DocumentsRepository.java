package com.example.ase2022y203.candidateDocuments.data;

import com.example.ase2022y203.candidateDocuments.domain.Documents;
import com.example.ase2022y203.candidateDocuments.service.DocumentsDTOSave;

import java.util.List;
import java.util.Optional;

public interface DocumentsRepository {
    List<Documents> getAllDocuments();
    void addNewDocument(DocumentsDTOSave documentsDTOSave);
    Optional<Documents> getDocument(String fileName);
}
