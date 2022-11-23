package com.example.ase2022y203.candidates.web.mock;

import com.example.ase2022y203.Ase2022Y203Application;
import com.example.ase2022y203.candidate.domain.Candidate;
import com.example.ase2022y203.candidate.service.CandidateAssembler;
import com.example.ase2022y203.candidate.service.CandidateDTO;
import com.example.ase2022y203.candidate.service.CandidateService;
import com.example.ase2022y203.candidate.service.messages.CandidateListRequest;
import com.example.ase2022y203.candidate.service.messages.CandidateListResponse;
import com.example.ase2022y203.candidate.web.CandidateController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;




@SpringBootTest
@AutoConfigureMockMvc
public class GetCandidates {

    @Autowired
    MockMvc mvc;

    @Autowired
    private CandidateService candidateService;

    @Test
    public void shouldGetCandidatesInformation() throws Exception {

        //Given candidates are requested

        CandidateListRequest candidateListRequest = CandidateListRequest
                .of()
                .build();

        //When the profile page is called it should contain this fragment
        String HTMLFragment = """
                <h2 class="page-text">Terry Houston from Farmland</h2>""";

        MvcResult result =
                mvc.perform(get("/candidate/candidate-profile/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        //Then the page should return with fragment
        assertTrue(content.contains(HTMLFragment));
    }

}
