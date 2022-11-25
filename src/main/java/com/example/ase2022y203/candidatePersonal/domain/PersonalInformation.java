package com.example.ase2022y203.candidatePersonal.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@Table
public class PersonalInformation {
    @Id
    private Integer id;
    @MappedCollection(idColumn="CID")
    private Integer cid;
    private String national_insurance;
    private String ethnicity;
    private String gender;
    private String age;
    private String sexuality;
}
