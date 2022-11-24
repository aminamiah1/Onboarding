package com.example.ase2022y203.candidatePersonal.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@Table
public class CandidatePersonal {
    private Integer id;
    @MappedCollection(idColumn="C_ID")
    private Integer c_id;
    private String national_insurance;
    private String ethnicity;
    private String gender;
    private String age;
    private String sexuality;
}
