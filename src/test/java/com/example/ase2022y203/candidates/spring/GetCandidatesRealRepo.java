package com.example.ase2022y203.candidates.spring;


import com.example.ase2022y203.candidate.domain.Candidate;
import com.example.ase2022y203.candidate.service.CandidateDTO;
import com.example.ase2022y203.candidate.service.CandidateService;
import com.example.ase2022y203.candidate.service.messages.CandidateListRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class GetCandidatesRealRepo {
    @Autowired
    private CandidateService candidateService;

    @Test
    public void shouldGetThreeCandidates() throws Exception {
        //Given the number of candidates in the system is two
        List<CandidateDTO> candidates;

        CandidateListRequest candidateListRequest = CandidateListRequest
                .of()
                .build();

        //When the candidates are retrieved
        var candidateListResponse = candidateService.getCandidates(candidateListRequest);

        //Then the size of the candidate list should be five
        assertEquals(5, candidateListResponse.getCandidates().size());
    }

    @Test
    public void shouldGetTerry() throws Exception {
        //Given a list of candidates
        List<CandidateDTO> candidates;

        CandidateListRequest candidateListRequest = CandidateListRequest
                .of()
                .build();

        //When the candidate with id of 2 is retrieved
        var candidateResponse = candidateService.getCandidateByID(2);

        //Then the candidate retrieved with the id of 2 should have the name terry
        assertEquals("Terry", candidateResponse.get().getFirst_name());
    }

    @Test
    public void shouldGetAllCandidates() throws Exception {
        //Given the number of candidates are 5
        List<CandidateDTO> candidates;
        CandidateListRequest candidateListRequest = CandidateListRequest
                .of()
                .build();
        //When the candidates are retrieved
        var candidateListResponse = candidateService.getCandidates(candidateListRequest);
        //Then the size of the candidate list should be 5
        assertEquals(5, candidateListResponse.getCandidates().size());
    }
}
