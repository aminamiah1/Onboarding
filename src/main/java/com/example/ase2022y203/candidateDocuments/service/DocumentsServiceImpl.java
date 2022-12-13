package com.example.ase2022y203.candidateDocuments.service;

import com.example.ase2022y203.candidateDocuments.data.DocumentsRepository;
import com.example.ase2022y203.candidateDocuments.domain.Documents;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentsServiceImpl implements DocumentsService {

    private final DocumentsRepository documentsRepository;

    public DocumentsServiceImpl(DocumentsRepository documentsRepository) {
        this.documentsRepository = documentsRepository;
    }

    @Override
    public List<DocumentsDTO> getAllDocuments() {
        List<Documents> documents = documentsRepository.getAllDocuments();
        return DocumentsAssembler.toDto(documents);
    }

    @Override
    public void save(DocumentsDTO documentsDTO) {
        Documents newDocuments = new Documents(
                documentsDTO.getId(),
                documentsDTO.getCid(),
                documentsDTO.getDocumentName(),
                documentsDTO.getDocumentType(),
                documentsDTO.getDocumentStatus()
        );
        documentsRepository.save(newDocuments);
    }

    @Override
    public Optional<DocumentsDTO> getDocument(String fileName) {
        Optional<Documents> aDocument = documentsRepository.getDocument(fileName);
        if(aDocument.isPresent()){
            return Optional.of(DocumentsAssembler.toDto(aDocument.get()));
        } else {
            return Optional.empty();
        }
    }
}
