package com.example.ase2022y203.candidatesLogin.web.mock;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SecurityTestSuite{

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private FilterChainProxy filterChainProxy;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void loggedInUserCanAccessCandidatesPath() throws Exception {
        //Given the logged in candidate tries to access the candidate path
        mockMvc = MockMvcBuilders.webAppContextSetup(context).dispatchOptions(true)
                .addFilters(filterChainProxy).build();
        //When they access the url
        //Then the url should be available but has no candidate details loaded, therefore not found
        mockMvc.perform(get("/candidate")
                        .with((user("USER"))))
                .andExpect(status().isNotFound());
    }

    @Test
    public void nonLoggedInUserCannotAccessCandidatesPath() throws Exception {
        //Given a non-logged in candidate tries to access the candidate path
        mockMvc = MockMvcBuilders.webAppContextSetup(context).dispatchOptions(true)
                .addFilters(filterChainProxy).build();
        //When they access the url
        //They should be redirected
        mockMvc.perform(get("/candidate")).andExpect(status().is3xxRedirection());
    }

    @Test
    public void errorPageShouldContainAVagueDescription() throws Exception{
            //Given the candidate tries to access a page that does not exist
            String HTMLFragment = """
             This page does not exist...""";

            //When they encounter an error
            MvcResult result =
                    mockMvc.perform(get("/404"))
                            .andDo(print())
                            .andExpect(status().isOk())
                            .andReturn();

            String content = result.getResponse().getContentAsString();

            //Then the error page should return with this fragment
            assertTrue(content.contains(HTMLFragment));
    }
}
