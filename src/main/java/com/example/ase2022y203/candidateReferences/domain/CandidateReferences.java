package com.example.ase2022y203.candidateReferences.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@Table
public class CandidateReferences {
    @Id
    private Integer id;
    @MappedCollection(idColumn="CID")
    private Integer cid;
    private String referee_name;
    private String referee_phone_number;

}
