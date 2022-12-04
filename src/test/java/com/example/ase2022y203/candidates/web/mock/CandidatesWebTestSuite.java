package com.example.ase2022y203.candidates.web.mock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;




@SpringBootTest
@AutoConfigureMockMvc
public class CandidatesWebTestSuite {

    @Autowired
    MockMvc mvc;

    @Test
    public void shouldGetHomepage() throws Exception {

        //Given the candidate loads the homepage
        //When the index page is called it should contain this fragment
        String HTMLFragment = """
               <h5 class="title-container-1">WHAT MAKES INITIA UNIQUE?</h5>""";

        MvcResult result =
                mvc.perform(get("/index"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        //Then the page should return with fragment
        assertTrue(content.contains(HTMLFragment));
    }

    @Test
    public void shouldGet404() throws Exception {
        //Given the user accesses a page that does not exist when they visit the page then they should get redirected
        //to the 404 message page
        mvc.perform(get("/notFound")).andExpect(status().isNotFound());
    }

}
