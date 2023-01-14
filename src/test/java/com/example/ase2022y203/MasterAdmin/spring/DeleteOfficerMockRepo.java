package com.example.ase2022y203.MasterAdmin.spring;

import com.example.ase2022y203.vettingOfficers.data.VetOfficerRepository;
import com.example.ase2022y203.vettingOfficers.service.VettingOfficersDTO;
import com.example.ase2022y203.vettingOfficers.service.VettingOfficersService;
import com.example.ase2022y203.vettingOfficers.service.messages.OfficersListRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DeleteOfficerMockRepo {

    @Autowired
    private VettingOfficersService vettingOfficersService;

    @MockBean
    private VetOfficerRepository vetOfficerRepository;

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void shouldDeleteOfficersByID() {
        //GIVEN
        OfficersListRequest officersListRequest = OfficersListRequest.of().build();
        //WHEN
        vettingOfficersService.delete(new VettingOfficersDTO(1, "Sarah", "Radcliffe",
                "SR@gmail.com",
                "$2a$12$u8rpeK5sRXjUuGxASxtHEeLIAqXtCLrosyjAh2sNHoo93ovm.8qZS"));
        var response = vettingOfficersService.getOfficers(officersListRequest);
        //THEN
        assertEquals(0, response.getOfficersDTOS().size());
    }
}