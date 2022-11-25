package com.example.ase2022y203.register.data;

import com.example.ase2022y203.candidate.domain.Candidate;
import com.example.ase2022y203.register.domain.Register;
import com.example.ase2022y203.register.service.RegisterDTO;

import java.util.List;
import java.util.Optional;

public interface RegisterRepository {

    List<Candidate> getCandidates();
    Optional<RegisterDTO> getRegistersResponseByID(Integer Id);
    void save(Register newRegisters);
}
