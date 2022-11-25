package com.example.ase2022y203.candidatePersonal.service;

import com.example.ase2022y203.candidatePersonal.service.messages.SaveCandidatePersonalRequest;
import com.example.ase2022y203.candidatePersonal.service.messages.SaveCandidatePersonalResponse;
import com.example.ase2022y203.candidatePersonal.service.messages.SingleCandidatePersonalRequest;
import com.example.ase2022y203.candidatePersonal.service.messages.SingleCandidatePersonalResponse;

public interface CandidatePersonalService {
    SingleCandidatePersonalResponse getCandidatePersonalByRequest(SingleCandidatePersonalRequest
                                                                         singleCandidatePersonalRequest);
    SaveCandidatePersonalResponse process(SaveCandidatePersonalRequest newCandidatePersonalRequest);
}
