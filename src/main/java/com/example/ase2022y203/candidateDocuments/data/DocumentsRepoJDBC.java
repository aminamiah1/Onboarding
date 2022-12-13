package com.example.ase2022y203.candidateDocuments.data;

import com.example.ase2022y203.candidateDocuments.domain.Documents;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DocumentsRepoJDBC extends CrudRepository<Documents, Integer> {
    List<Documents> findAll();
    Documents findAllByDocumentNameLike(String fileName);
}
