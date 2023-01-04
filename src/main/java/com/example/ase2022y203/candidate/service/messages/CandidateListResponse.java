package com.example.ase2022y203.candidate.service.messages;

import com.example.ase2022y203.candidate.service.CandidateDTO;
import com.example.ase2022y203.candidate.service.CandidateDTOReg;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor
@Builder(builderMethodName = "of")
public class CandidateListResponse {
    private CandidateListRequest request;
    private List<CandidateDTO> candidates;
    private List<CandidateDTOReg> allCandidates;
    private List<CandidateDTO> candidatesAPI;
}
