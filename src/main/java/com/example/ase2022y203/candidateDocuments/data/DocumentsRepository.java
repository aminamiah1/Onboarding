package com.example.ase2022y203.candidateDocuments.data;

import com.example.ase2022y203.candidateDocuments.domain.Documents;

import java.util.List;

public interface DocumentsRepository {
    List<Documents> getAllDocuments();
    void save(Documents newDocuments);
    Documents getDocument(String fileName);
}
