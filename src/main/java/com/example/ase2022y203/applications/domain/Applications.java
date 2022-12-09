package com.example.ase2022y203.applications.domain;

import com.example.ase2022y203.candidate.domain.Candidate;
import com.example.ase2022y203.candidate.service.CandidateDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@Table
public class Applications {
    @Id
    private Integer id;
    private String app_status;
    @MappedCollection(idColumn = "id")
    Candidate cid;
}
