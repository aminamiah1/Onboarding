package com.example.ase2022y203.applications.service;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ApplicationsDTO {
    private Integer id;
    private String app_status;
    private Integer cid;
}
