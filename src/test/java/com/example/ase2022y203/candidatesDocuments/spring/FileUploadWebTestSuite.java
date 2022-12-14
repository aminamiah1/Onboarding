package com.example.ase2022y203.candidatesDocuments.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FileUploadWebTestSuite {
    @MockBean
    private MockMultipartFile mockFile;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private FilterChainProxy filterChainProxy;

    @Test
    public void testFileShouldNotUploadAsEmpty() throws Exception {
        //Given the candidate tries to upload an empty file
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.multipart("/uploadID")
                .file(mockFile)).andReturn();
        //When they try to upload the file
        //Then they should get a 400 error returned as it is empty
        assertEquals(400, result.getResponse().getStatus());
    }

    @Test
    public void websiteVisitorShouldNotBeAbleToViewDocuments() throws Exception {
        //Given a non-logged in user tries to access the view document path
        mockMvc = MockMvcBuilders.webAppContextSetup(context).dispatchOptions(true)
                .addFilters(filterChainProxy).build();
        //When they access the url
        //They should be redirected
        mockMvc.perform(get("/view-document/1")).andExpect(status().is3xxRedirection());
    }

    @Test
    public void shouldNotBeAbleToAccessFileDeletePathWithoutLogin() throws Exception {
        //Given a user tries to delete a file without logging in
        mockMvc = MockMvcBuilders.webAppContextSetup(context).dispatchOptions(true)
                .addFilters(filterChainProxy).build();
        //When the user accesses the file delete url then they should get an error
        mockMvc.perform(get("/deleteCandidatePassport/1")).andExpect(status().is4xxClientError());
    }
}
