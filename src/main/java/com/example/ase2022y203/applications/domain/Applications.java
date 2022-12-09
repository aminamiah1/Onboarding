package com.example.ase2022y203.applications.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@Table
public class Applications {
    @Id
    private Integer id;
    private String app_status;
    @MappedCollection(idColumn = "CID")
    private Integer cid;
}
