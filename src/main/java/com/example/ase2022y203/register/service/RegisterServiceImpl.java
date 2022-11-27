package com.example.ase2022y203.register.service;

import com.example.ase2022y203.register.data.RegisterRepository;
import com.example.ase2022y203.register.domain.Register;
import com.example.ase2022y203.register.service.messages.SaveRegisterRequest;
import com.example.ase2022y203.register.service.messages.SaveRegistersResponse;
import com.example.ase2022y203.register.service.messages.SingleRegisterRequest;
import com.example.ase2022y203.register.service.messages.SingleRegisterResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public abstract class RegisterServiceImpl implements RegisterService {
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

    @Override
    public SingleRegisterResponse getRegistersByRequest(SingleRegisterRequest singleRegisterRequest) {
        Optional<Register> aRegister = registerRepository.getRegistersID
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

    }

    @Override
    public Optional<RegisterDTO> getRegistersID(Integer id) {
        Optional<Register> aRegister = registerRepository.getRegistersID(id);
        if (aRegister.isPresent()) {
            System.out.println(aRegister.get());
            return Optional.of(RegisterAssembler.toDto(aRegister.get()));
        } else {
            return Optional.empty();
        }
    }
}
