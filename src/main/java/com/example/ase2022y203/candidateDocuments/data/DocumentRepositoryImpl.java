package com.example.ase2022y203.candidateDocuments.data;

import com.example.ase2022y203.candidateDocuments.domain.Documents;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    }

    @Override
    public Documents getDocument(String fileName) {
        return documentsRepoJDBC.findAllByDocumentNameLike(fileName);
    }
}
