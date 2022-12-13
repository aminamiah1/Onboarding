package com.example.ase2022y203.candidateDocuments.data;

import com.example.ase2022y203.candidateDocuments.domain.Documents;
import com.example.ase2022y203.candidateDocuments.service.DocumentsDTOSave;
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
    public void addNewDocument(DocumentsDTOSave documentsDTOSave) {
        String addNewDocumentSQL ="INSERT INTO Documents (cid, document_name, document_type, document_status) VALUES(?, ?, ?, ?)";
        jdbc.update(addNewDocumentSQL, documentsDTOSave.getCid().getId(), documentsDTOSave.getDocumentName(), documentsDTOSave.getDocumentType()
        , documentsDTOSave.getDocumentStatus());
    }

    @Override
    public Optional<Documents> getDocument(String fileName) {
        return documentsRepoJDBC.findAllByDocumentNameLike(fileName);
    }
}
