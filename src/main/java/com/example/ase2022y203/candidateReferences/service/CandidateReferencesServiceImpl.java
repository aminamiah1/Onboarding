package com.example.ase2022y203.candidateReferences.service;

import com.example.ase2022y203.candidateReferences.data.CandidateReferencesRepository;
import com.example.ase2022y203.candidateReferences.domain.CandidateReferences;
import com.example.ase2022y203.candidateReferences.service.messages.CandidateRefListRequest;
import com.example.ase2022y203.candidateReferences.service.messages.DeleteRefResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CandidateReferencesServiceImpl implements CandidateReferencesService {

    private final CandidateReferencesRepository candidateReferencesRepository;

    public CandidateReferencesServiceImpl(CandidateReferencesRepository crRepo) {
        this.candidateReferencesRepository = crRepo;
    }

    @Override
    public List<CandidateReferencesDTO> getCandidateReferencesByCID(Integer cid) {
        List<CandidateReferences> candidateReferences;

        candidateReferences = candidateReferencesRepository.getCandidateReferencesByCID(cid);

        return candidateReferences.stream().map(cr -> CandidateReferencesAssembler.toDto(cr)).collect(Collectors.toList());
    }

    @Override
    public void addNewReference(CandidateReferencesDTOSave referenceDTO) {
        CandidateReferences newReference = new CandidateReferences(
                candidateReferencesRepository.getReferences()
                        .get(candidateReferencesRepository.getReferences().size() - 1)
                        .getId() + 1,
                referenceDTO.getC_id(),
                referenceDTO.getReferee_name(),
                referenceDTO.getReferee_phone_number()
        );
        candidateReferencesRepository.save(newReference);
    }

    @Override
    public void updateReference(CandidateReferencesDTO referenceDTO) {
        CandidateReferences updatedReference = new CandidateReferences(
                referenceDTO.getId(),
                referenceDTO.getC_id(),
                referenceDTO.getReferee_name(),
                referenceDTO.getReferee_phone_number()
        );
        candidateReferencesRepository.update(updatedReference);
    }

    @Override
    public void deleteReference(CandidateReferencesDTO referenceDTO) {
        CandidateReferences deleteReference = new CandidateReferences(
                referenceDTO.getId(),
                referenceDTO.getC_id(),
                referenceDTO.getReferee_name(),
                referenceDTO.getReferee_phone_number()
        );
        candidateReferencesRepository.delete(deleteReference);
    }


}
