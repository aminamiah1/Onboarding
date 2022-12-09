package com.example.ase2022y203.applications.service;

import com.example.ase2022y203.candidate.domain.Candidate;
import com.example.ase2022y203.candidate.service.CandidateDTO;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;
import java.util.Set;

@Value
@AllArgsConstructor
public class ApplicationsDTO {
    private Integer id;
    private String appstatus;
    private Candidate cid;
}
