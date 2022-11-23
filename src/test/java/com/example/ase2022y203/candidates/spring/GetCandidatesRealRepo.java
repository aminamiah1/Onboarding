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
    public void shouldGetTwoCandidates() throws Exception {
        //Given the number of candidates in the system is two
        List<CandidateDTO> candidates;

        CandidateListRequest candidateListRequest = CandidateListRequest
                .of()
                .build();

        //When the candidates are retrieved
        var candidateListResponse = candidateService.getCandidates(candidateListRequest);

        //Then the size of the candidate list should be two
        assertEquals(3, candidateListResponse.getCandidates().size());
    }

}
