package com.example.ase2022y203.candidate.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.relational.core.mapping.MappedCollection;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@Table
public class Candidate {
    @Id
    private Integer id;
    @MappedCollection(idColumn="CID")
    private Integer cid;
    private String first_name;
    private String surname;
    private String email;
    private String password;
    private String company_name;
}
