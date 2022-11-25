package com.example.ase2022y203.register.service;

import com.example.ase2022y203.register.data.RegisterRepository;
import com.example.ase2022y203.register.domain.Register;
import com.example.ase2022y203.register.service.messages.SaveRegisterRequest;
import com.example.ase2022y203.register.service.messages.SaveRegistersResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterServiceImpl implements RegisterService {
    private final RegisterRepository registerRepository;

    public RegisterServiceImpl(RegisterRepository rRepo) {
        this.registerRepository = rRepo;
    }

    @Override
    public SaveRegistersResponse process(SaveRegisterRequest saveRegisterRequest) {

        RegisterDTO registerDTO = saveRegisterRequest.getRegisterDTO();

         Register newRegister = new Register(registerDTO.getID(), registerDTO.getFirst_name(),
                 registerDTO.getSurname(), registerDTO.getEmail(), registerDTO.getPassword());

        registerRepository.save(newRegister);

        return SaveRegistersResponse.of().request(saveRegisterRequest).build();
    }
}

/* @Override
    public SingleRegisterResponse getRegisterByRequest(SingleRegisterRequest singleRegisterRequest) {
        Optional<Register> aRegister = registerRepository.getRegistersResponseByID
                (singleRegisterRequest.getId());

        RegisterDTO registerDTO;

        if (aRegister.isPresent()) {
            registerDTO = RegisterAssembler.toDto(aRegister.get());
        } else {
            registerDTO = null;
        }

        return SingleRegisterResponse.of()
                .request(singleRegisterRequest)
                .registerDTO(registerDTO)
                .build();
    } */

