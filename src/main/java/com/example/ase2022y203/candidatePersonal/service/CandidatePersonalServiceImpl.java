package com.example.ase2022y203.candidatePersonal.service;

import com.example.ase2022y203.candidatePersonal.data.CandidatePersonalRepository;
import com.example.ase2022y203.candidatePersonal.domain.PersonalInformation;
import com.example.ase2022y203.candidatePersonal.service.messages.SingleCandidatePersonalRequest;
import com.example.ase2022y203.candidatePersonal.service.messages.SingleCandidatePersonalResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CandidatePersonalServiceImpl implements CandidatePersonalService{

    private final CandidatePersonalRepository candidatePersonalRepository;
    public CandidatePersonalServiceImpl(CandidatePersonalRepository cpRepo) {
        this.candidatePersonalRepository = cpRepo;
    }

    @Override
    public SingleCandidatePersonalResponse getCandidatePersonalByRequest(SingleCandidatePersonalRequest
                                                                                    singleCandidatePersonalRequest) {
        Optional<PersonalInformation> aCandidatePersonal = candidatePersonalRepository.getCandidatePersonalInfoByCID(
                singleCandidatePersonalRequest.getCid());

        CandidatePersonalDTO candidatePersonalDTO;

        if(aCandidatePersonal.isPresent()){
            candidatePersonalDTO = CandidatePersonalAssembler.toDto(aCandidatePersonal.get());
        } else {
            candidatePersonalDTO = null;
        }

        return SingleCandidatePersonalResponse.of()
                .request(singleCandidatePersonalRequest)
                .candidatePersonalDTO(candidatePersonalDTO)
                .build();

    }

}
