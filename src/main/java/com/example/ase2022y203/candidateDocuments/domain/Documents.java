package com.example.ase2022y203.candidateDocuments.domain;

import com.example.ase2022y203.candidate.domain.Candidate;
import com.example.ase2022y203.candidateDocuments.service.DocumentsDTOSave;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@Table(name="documents")
public class Documents {
    @Id
    private Integer id;
    @MappedCollection(idColumn = "id")
    Candidate cid;
    private String documentName;
    private String documentType;
    private String documentStatus;

    public Documents(){

    }

    public Documents(Candidate cid, String documentName, String documentType, String documentStatus) {
    }

}
