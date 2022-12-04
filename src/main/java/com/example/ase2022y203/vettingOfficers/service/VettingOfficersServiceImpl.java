package com.example.ase2022y203.vettingOfficers.service;

import com.example.ase2022y203.vettingOfficers.data.VetOfficerRepository;
import com.example.ase2022y203.vettingOfficers.domain.VettingOfficers;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VettingOfficersServiceImpl implements VettingOfficersService {

    private final VetOfficerRepository vetOfficerRepository;

    public VettingOfficersServiceImpl(VetOfficerRepository repo) {
        this.vetOfficerRepository = repo;
    }

    @Override
    public Optional<VettingOfficersDTO> getVettingOfficerByEmail(String email) {
        Optional<VettingOfficers> aVettingOfficer = vetOfficerRepository.getVettingOfficerByEmail(email);
        if(aVettingOfficer.isPresent()){
            System.out.println(aVettingOfficer.get());
            return Optional.of(VettingOfficersAssembler.toDto(aVettingOfficer.get()));
        } else {
            return Optional.empty();
        }
    }

}
