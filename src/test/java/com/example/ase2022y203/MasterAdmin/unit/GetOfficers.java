package com.example.ase2022y203.MasterAdmin.unit;

import com.example.ase2022y203.vettingOfficers.data.VetOfficerRepository;
import com.example.ase2022y203.vettingOfficers.data.VetOfficerRepositoryImpl;
import com.example.ase2022y203.vettingOfficers.domain.VettingOfficers;
import com.example.ase2022y203.vettingOfficers.service.VettingOfficersDTO;
import com.example.ase2022y203.vettingOfficers.service.VettingOfficersService;
import com.example.ase2022y203.vettingOfficers.service.VettingOfficersServiceImpl;
import com.example.ase2022y203.vettingOfficers.service.messages.OfficersListRequest;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class GetOfficers {

    private VettingOfficersService vettingOfficersService;

    private VetOfficerRepository vetOfficerRepository;

    @Test
    public void shouldGetThreeVettingOfficers() throws Exception {
        //Given the number of vetting officers added to the system is three
        VettingOfficers v1 = new VettingOfficers(1, "Harry", "Peters",
                "HP@gmail.com", "IamHarryP@123");
        VettingOfficers v2 = new VettingOfficers(2,"Xiaohan","Du",
                "XD@gmail.com","XD123@");
        VettingOfficers v3 = new VettingOfficers(3,"Mark","Evans",
                "ME@gmail.com","ME@123");

        vetOfficerRepository = mock(VetOfficerRepositoryImpl.class);

        given(vetOfficerRepository.getOfficers()).willReturn(List.of(v1,v2,v3));

        vettingOfficersService = new VettingOfficersServiceImpl(vetOfficerRepository);

        List<VettingOfficersDTO> officersDTO;

        OfficersListRequest officersListRequest = OfficersListRequest
                .of()
                .build();

        //When the officers are retrieved
        var officersListResponse = vettingOfficersService.getOfficers(officersListRequest);

        //Then the size of the officers list should be three
        assertEquals(3, officersListResponse.getOfficersDTOS().size());
    }

    @Test
    public void shouldGetOfficersByID() throws Exception {
        VettingOfficers v1 = new VettingOfficers(1, "Harry", "Peters",
                "HP@gmail.com", "IamHarryP@123");
        VettingOfficers v2 = new VettingOfficers(2,"Xiaohan","Du",
                "XD@gmail.com","XD123@");
        VettingOfficers v3 = new VettingOfficers(3,"Mark","Evans",
                "ME@gmail.com","ME@123");

        vetOfficerRepository = mock(VetOfficerRepositoryImpl.class);

        given(vetOfficerRepository.getVettingOfficerById(Optional.of(1))).willReturn(Optional.of(v1));

        vettingOfficersService = new VettingOfficersServiceImpl(vetOfficerRepository);

        List<VettingOfficersDTO> vettingOfficersDTOS;

        OfficersListRequest officersListRequest = OfficersListRequest
                .of()
                .build();

        //When the officer with the id of 1 is requested
        var officerListResponse = vettingOfficersService.getVettingOfficerById(Optional.of(1));

        //Then the officer with id of 1 should be returned
        assertEquals("Harry", officerListResponse.get().getFirst_name());
    }
}
