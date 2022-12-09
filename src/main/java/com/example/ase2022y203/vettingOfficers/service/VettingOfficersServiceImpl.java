package com.example.ase2022y203.vettingOfficers.service;

import com.example.ase2022y203.vettingOfficers.data.VetOfficerRepository;
import com.example.ase2022y203.vettingOfficers.domain.VettingOfficers;
import com.example.ase2022y203.vettingOfficers.service.messages.OfficersListRequest;
import com.example.ase2022y203.vettingOfficers.service.messages.OfficersListResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VettingOfficersServiceImpl implements VettingOfficersService {

    private final VetOfficerRepository vetOfficerRepository;

    public VettingOfficersServiceImpl(VetOfficerRepository repo) {
        this.vetOfficerRepository = repo;
    }

    private List<VettingOfficersDTO> getOfficers() {
        List<VettingOfficers> officers = vetOfficerRepository.getOfficers();
        return VettingOfficersAssembler.toDto(officers);
    }

    public OfficersListResponse getOfficers(OfficersListRequest officersListRequest){
        List<VettingOfficersDTO> officers;
        officers = getOfficers();
        return OfficersListResponse.of()
                .request(officersListRequest)
                .request(officersListRequest)
                .officersDTOS(officers)
                .build();
    }

    @Override
    public Optional<VettingOfficersDTO> getVettingOfficerByEmail(String email) {
        Optional<VettingOfficers> aVettingOfficer = vetOfficerRepository.getVettingOfficerByEmail(email);
        if (aVettingOfficer.isPresent()) {
            System.out.println(aVettingOfficer.get());
            return Optional.of(VettingOfficersAssembler.toDto(aVettingOfficer.get()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void addNewAdmin(VettingOfficersDTO officerDTO) {
        VettingOfficers officers = new VettingOfficers(
                vetOfficerRepository.getOfficers()
                        .get(vetOfficerRepository.getOfficers().size() - 1)
                        .getId() + 1,
                officerDTO.getFirst_name(),
                officerDTO.getSurname(),
                officerDTO.getEmail(),
                officerDTO.getPassword()
        );
        vetOfficerRepository.save(officers);
    }
}
