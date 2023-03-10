package com.example.ase2022y203.candidateReferences.web.mock;

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
public class ReferencesWebTestSuite{
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private FilterChainProxy filterChainProxy;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldNotBeAbleToAccessReferencePathWithoutLogin() throws Exception {

        //Given a user tries to access the reference page without logging in
        mockMvc = MockMvcBuilders.webAppContextSetup(context).dispatchOptions(true)
                .addFilters(filterChainProxy).build();
        //When the user accesses the reference url then they should get an error
        mockMvc.perform(get("/reference")).andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldNotBeAbleToAccessReferenceDeletePathWithoutLogin() throws Exception {

        //Given a user tries to delete a reference without logging in
        mockMvc = MockMvcBuilders.webAppContextSetup(context).dispatchOptions(true)
                .addFilters(filterChainProxy).build();
        //When the user accesses the reference delete url then they should get an error
        mockMvc.perform(get("/reference/delete/1")).andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldNotBeAbleToAccessReferenceEditPathWithoutLogin() throws Exception {

        //Given a user tries to edit a reference without logging in
        mockMvc = MockMvcBuilders.webAppContextSetup(context).dispatchOptions(true)
                .addFilters(filterChainProxy).build();
        //When the user accesses the edit reference url then they should get an error
        mockMvc.perform(get("/reference/edit/2")).andExpect(status().is4xxClientError());
    }
}
