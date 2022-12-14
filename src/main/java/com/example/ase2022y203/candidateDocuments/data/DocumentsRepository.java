package com.example.ase2022y203.candidateDocuments.data;

import com.example.ase2022y203.candidateDocuments.domain.Documents;
import com.example.ase2022y203.candidateDocuments.service.DocumentsDTO;
import com.example.ase2022y203.candidateDocuments.service.DocumentsDTOSave;

import java.util.List;
import java.util.Optional;

public interface DocumentsRepository {
    List<Documents> getAllDocuments();
    List<Documents> getAllPassportFiles();
    List<Documents> getAllIDFiles();
    void addNewDocument(DocumentsDTOSave documentsDTOSave);
    Optional<Documents> getDocument(String fileName);
    Optional<Documents> getDocumentByID(Integer id);
    void deleteDocument(Documents documents);
}
