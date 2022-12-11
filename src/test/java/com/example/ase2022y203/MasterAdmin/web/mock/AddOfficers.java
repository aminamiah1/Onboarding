package com.example.ase2022y203.MasterAdmin.web.mock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AddOfficers {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private FilterChainProxy filterChainProxy;

    @Autowired
    private MockMvc mockMvc;

    //Security Testing
    @Test
    public void loggedInMasterAdminCanAccessAddOfficerPath() throws Exception {
        //Given the logged in Master Admin tries to access the Add Officer path
        mockMvc = MockMvcBuilders.webAppContextSetup(context).dispatchOptions(true)
                .addFilters(filterChainProxy).build();
        //When they access the url
        //Then the url should be available
        mockMvc.perform(get("/officer/add"));
    }

    @Test
    public void nonLoggedInUserCannotAccessCandidatesPath() throws Exception {
        //Given a non-logged in master admin tries to access the add officers path
        mockMvc = MockMvcBuilders.webAppContextSetup(context).dispatchOptions(true)
                .addFilters(filterChainProxy).build();
        //When they access the url
        //They should be redirected
        mockMvc.perform(get("/officer/add")).andExpect(status().is3xxRedirection());
    }
}
