package com.example.ase2022y203.candidateReferences.service;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CandidateReferencesDTOSave {
    private Integer c_id;
    private String referee_name;
    private String referee_phone_number;
}
