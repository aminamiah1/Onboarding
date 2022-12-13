package com.example.ase2022y203.MasterAdmin.web.mock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class MasterAdminWebTestSuite {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private FilterChainProxy filterChainProxy;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldNotBeAbleToAccessALlOfficersPathWithoutLogin() throws Exception {

        //Given a user tries to access the all officers path without logging in
        mockMvc = MockMvcBuilders.webAppContextSetup(context).dispatchOptions(true)
                .addFilters(filterChainProxy).build();

        //When the user accesses the all officers url then they should get an error
        mockMvc.perform(get("/all-officers")).andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldNotBeAbleToAccessAccessEditPathWithoutLogin() throws Exception {

        //Given a user tries to access the edit path without logging in
        mockMvc = MockMvcBuilders.webAppContextSetup(context).dispatchOptions(true)
                .addFilters(filterChainProxy).build();

        //When the user accesses the edit url then they should get an error
        mockMvc.perform(get("/officers/edit/2")).andExpect(status().is4xxClientError());

    }

    @Test
    public void shouldNotBeAbleToAccessAccessDeletePathWithoutLogin() throws Exception {

        //Given a user tries to access the delete path without logging in
        mockMvc = MockMvcBuilders.webAppContextSetup(context).dispatchOptions(true)
                .addFilters(filterChainProxy).build();

        //When the user accesses the delete url then they should get an error
        mockMvc.perform(get("/officers/delete/2")).andExpect(status().is4xxClientError());

    }
}
