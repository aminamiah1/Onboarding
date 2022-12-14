package com.example.ase2022y203.candidateDocuments.data;

import com.example.ase2022y203.candidate.data.CandidateRepository;
import com.example.ase2022y203.candidateDocuments.domain.Documents;
import com.example.ase2022y203.candidateDocuments.service.DocumentsDTOSave;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DocumentRepositoryImpl implements DocumentsRepository {

    private final JdbcTemplate jdbc;
    private final DocumentsRepoJDBC documentsRepoJDBC;
    private final CandidateRepository candidateRepository;
    private RowMapper<Documents> documentsMapper;

    public DocumentRepositoryImpl(JdbcTemplate jdbc, DocumentsRepoJDBC documentsRepoJDBC, CandidateRepository candidateRepository) {
        this.jdbc = jdbc;
        this.documentsRepoJDBC = documentsRepoJDBC;
        this.candidateRepository = candidateRepository;
        setDocumentsMapper();
    }

    private void setDocumentsMapper(){
        documentsMapper = (rs, i) -> new Documents(
                rs.getInt("id"),
                candidateRepository.getCandidateByID(rs.getInt("cid")).get(),
                rs.getString("document_name"),
                rs.getString("document_type"),
                rs.getString("document_status")
        );
    }

    @Override
    public List<Documents> getAllDocuments() {
        String allDocumentsSQL = "SELECT * FROM Documents";
        return jdbc.query(allDocumentsSQL, documentsMapper);
    }

    @Override
    public void addNewDocument(DocumentsDTOSave documentsDTOSave) {
        String addNewDocumentSQL ="INSERT INTO Documents (cid, document_name, document_type, document_status) VALUES(?, ?, ?, ?)";
        jdbc.update(addNewDocumentSQL, documentsDTOSave.getCid().getId(), documentsDTOSave.getDocumentName(), documentsDTOSave.getDocumentType()
        , documentsDTOSave.getDocumentStatus());
    }

    @Override
    public Optional<Documents> getDocument(String documentName) {
        return documentsRepoJDBC.findAllByDocumentNameIsLike(documentName);
    }

    @Override
    public Optional<Documents> getDocumentByID(Integer id) {
        return documentsRepoJDBC.findDocumentsById(id);
    }

    @Override
    public void deleteDocument(Documents documents) {
        String deleteQuery = "DELETE FROM Documents WHERE ID = ?";
        jdbc.update(deleteQuery, documents.getId());
    }
}
