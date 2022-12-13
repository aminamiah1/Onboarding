package com.example.ase2022y203.candidateDocuments.data;

import com.example.ase2022y203.candidateDocuments.domain.Documents;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DocumentRepositoryImpl implements DocumentsRepository {

    private final JdbcTemplate jdbc;
    private final DocumentsRepoJDBC documentsRepoJDBC;

    public DocumentRepositoryImpl(JdbcTemplate jdbc, DocumentsRepoJDBC documentsRepoJDBC) {
        this.jdbc = jdbc;
        this.documentsRepoJDBC = documentsRepoJDBC;
    }

    @Override
    public List<Documents> getAllDocuments() {
        return documentsRepoJDBC.findAll();
    }

    @Override
    public void save(Documents newDocuments) {
        documentsRepoJDBC.save(newDocuments);
    }

    @Override
    public Optional<Documents> getDocument(String fileName) {
        return documentsRepoJDBC.findAllByDocumentNameLike(fileName);
    }
}
