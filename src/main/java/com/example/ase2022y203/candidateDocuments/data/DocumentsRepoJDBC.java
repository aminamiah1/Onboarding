package com.example.ase2022y203.candidateDocuments.data;

import com.example.ase2022y203.candidateDocuments.domain.Documents;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DocumentsRepoJDBC extends CrudRepository<Documents, Integer> {
    Optional<Documents> findAllByDocumentNameIsLike(String documentName);
    Optional<Documents> findDocumentsById(Integer id);
}
