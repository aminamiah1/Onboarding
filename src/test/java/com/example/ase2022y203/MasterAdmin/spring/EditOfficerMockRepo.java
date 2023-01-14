package com.example.ase2022y203.MasterAdmin.spring;

import com.example.ase2022y203.vettingOfficers.data.VetOfficerRepository;
import com.example.ase2022y203.vettingOfficers.service.VettingOfficersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@SpringBootTest
public class EditOfficerMockRepo {
    @Autowired
    private VettingOfficersService vettingOfficersService;

    @MockBean
    private VetOfficerRepository vetOfficerRepository;
}