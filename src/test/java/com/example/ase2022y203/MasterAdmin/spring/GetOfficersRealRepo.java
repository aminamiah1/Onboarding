package com.example.ase2022y203.MasterAdmin.spring;

import com.example.ase2022y203.vettingOfficers.service.VettingOfficersDTO;
import com.example.ase2022y203.vettingOfficers.service.VettingOfficersService;
import com.example.ase2022y203.vettingOfficers.service.messages.OfficersListRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GetOfficersRealRepo {

    @Autowired
    private VettingOfficersService vettingOfficersService;

    @Test
    public void shouldGetThreeOfficers() throws Exception {
        //Given the number of officers in the system is three
        List<VettingOfficersDTO> officersDTO;

        OfficersListRequest officersListRequest = OfficersListRequest
                .of()
                .build();

        //When the officers are retrieved
        var officersListResponse = vettingOfficersService.getOfficers(officersListRequest);

        //Then the size of the officers list should be three
        assertEquals(3,officersListResponse.getOfficersDTOS().size());
    }

    @Test
    public void shouldGetEmily() throws Exception {
        //Given a list of officers
        List<VettingOfficersDTO> officersDTO;

        OfficersListRequest officersListRequest = OfficersListRequest
                .of()
                .build();

        //When the officers with id of 2 should have the name Emily
        var officersListResponse = vettingOfficersService.getVettingOfficerById(Optional.of(2));

        //Then the officer retrieved with the id of 2 should have the name of Emily
        assertEquals("Emily", officersListResponse.get().getFirst_name());
    }
}
