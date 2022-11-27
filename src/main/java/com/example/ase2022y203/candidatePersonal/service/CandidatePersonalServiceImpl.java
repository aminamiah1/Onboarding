package com.example.ase2022y203.candidatePersonal.service;

import com.example.ase2022y203.candidatePersonal.data.CandidatePersonalRepository;
import com.example.ase2022y203.candidatePersonal.domain.PersonalInformation;
import com.example.ase2022y203.candidatePersonal.service.messages.SaveCandidatePersonalRequest;
import com.example.ase2022y203.candidatePersonal.service.messages.SaveCandidatePersonalResponse;
import com.example.ase2022y203.candidatePersonal.service.messages.SingleCandidatePersonalRequest;
import com.example.ase2022y203.candidatePersonal.service.messages.SingleCandidatePersResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CandidatePersonalServiceImpl implements CandidatePersonalService{

    private final CandidatePersonalRepository candidatePersonalRepository;
    public CandidatePersonalServiceImpl(CandidatePersonalRepository cpRepo) {
        this.candidatePersonalRepository = cpRepo;
    }

    @Override
    public SingleCandidatePersResponse getCandidatePersonalByRequest(SingleCandidatePersonalRequest
                                                                                    singleCandidatePersonalRequest) {
        Optional<PersonalInformation> aCandidatePersonal = candidatePersonalRepository.getCandidatePersonalInfoByCID(
                singleCandidatePersonalRequest.getCid());

        CandidatePersonalDTO candidatePersonalDTO;

        if(aCandidatePersonal.isPresent()){
            candidatePersonalDTO = CandidatePersonalAssembler.toDto(aCandidatePersonal.get());
        } else {
            candidatePersonalDTO = null;
        }

        return SingleCandidatePersResponse.of()
                .request(singleCandidatePersonalRequest)
                .candidatePersonalDTO(candidatePersonalDTO)
                .build();

    }

    @Override
    public SaveCandidatePersonalResponse process(SaveCandidatePersonalRequest saveCandidatePersonalRequest) {

        Optional<PersonalInformation> personalInformation = candidatePersonalRepository
                .getCandidatePersonalInfoByCID(saveCandidatePersonalRequest.getCandidatePersonalDTO().getC_id());

        CandidatePersonalDTO candidatePersonalDTO = saveCandidatePersonalRequest.getCandidatePersonalDTO();

        PersonalInformation newPersonalInformation = new PersonalInformation(candidatePersonalDTO.getId(),
                candidatePersonalDTO.getC_id(), candidatePersonalDTO.getNational_insurance(), candidatePersonalDTO.getEthnicity(),
                candidatePersonalDTO.getGender(), candidatePersonalDTO.getAge(),
                candidatePersonalDTO.getSexuality());

        candidatePersonalRepository.save(newPersonalInformation);

        return SaveCandidatePersonalResponse.of().request(saveCandidatePersonalRequest).build();
    }

    @Override
    public Optional<CandidatePersonalDTO> getCandidatePersonalByCID(Integer cid) {
        Optional<PersonalInformation> aCandidatePersonal = candidatePersonalRepository.getCandidatePersonalInfoByCID(cid);
        if(aCandidatePersonal.isPresent()) {
            System.out.println(aCandidatePersonal.get());
            return Optional.of(CandidatePersonalAssembler.toDto(aCandidatePersonal.get()));
        } else {
            return Optional.empty();
        }
    }
}
