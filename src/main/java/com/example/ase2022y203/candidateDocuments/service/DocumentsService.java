package com.example.ase2022y203.candidateDocuments.service;

import com.example.ase2022y203.candidateDocuments.domain.Documents;

import java.util.List;
import java.util.Optional;

public interface DocumentsService {
    List<DocumentsDTO> getAllDocuments();
    List<DocumentsDTO> getAllIDFiles();
    List<DocumentsDTO> getAllPassportFiles();
    void save(DocumentsDTOSave documentsDTOSave);
    Optional<DocumentsDTO> getDocument(String fileName);
    Optional<DocumentsDTO> getDocumentByID(Integer id);
    void deleteDocument(DocumentsDTO documentsDTO);
}
