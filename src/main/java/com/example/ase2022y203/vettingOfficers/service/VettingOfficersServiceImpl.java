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
    public Optional<VettingOfficersDTO> getVettingOfficerById(Optional<Integer> id){
        Optional<VettingOfficers> vettingOfficers = vetOfficerRepository.getVettingOfficerById(id);
        if (vettingOfficers.isPresent()){
            System.out.println(vettingOfficers.get());
            return Optional.of(VettingOfficersAssembler.toDto(vettingOfficers.get()));
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

    @Override
    public void delete(VettingOfficersDTO vettingOfficersDTO){
        VettingOfficers deleteOfficer = new VettingOfficers(
                vettingOfficersDTO.getId(),
                vettingOfficersDTO.getFirst_name(),
                vettingOfficersDTO.getSurname(),
                vettingOfficersDTO.getEmail(),
                vettingOfficersDTO.getPassword()
                );
        vetOfficerRepository.delete(deleteOfficer);
    }

    @Override
    public void update(VettingOfficersDTO updateVettingOfficer){
        VettingOfficers updateVettingOfficers = new VettingOfficers(
                updateVettingOfficer.getId(),
                updateVettingOfficer.getFirst_name(),
                updateVettingOfficer.getSurname(),
                updateVettingOfficer.getEmail(),
                updateVettingOfficer.getPassword()
        );
        vetOfficerRepository.update(updateVettingOfficers);
    }
}
