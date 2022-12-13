package com.example.ase2022y203.candidateDocuments.data;

import com.example.ase2022y203.candidateDocuments.domain.Documents;

import java.util.List;
import java.util.Optional;

public interface DocumentsRepository {
    List<Documents> getAllDocuments();
    void save(Documents newDocuments);
    Optional<Documents> getDocument(String fileName);
}
