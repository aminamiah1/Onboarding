package com.example.ase2022y203.candidate.service.messages;

import com.example.ase2022y203.candidate.service.CandidateDTO;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderMethodName = "of")
public class SingleCandidateResponse {
    SingleCandidateRequest request;
    CandidateDTO candidateDTO;

    public Boolean isCandidatePresent(){
        return candidateDTO != null;
    }

}
