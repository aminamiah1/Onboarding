package com.example.ase2022y203.candidates.spring;

import com.example.ase2022y203.candidate.data.CandidateRepository;
import com.example.ase2022y203.candidate.data.CandidateRepositoryImpl;
import com.example.ase2022y203.candidate.domain.Candidate;
import com.example.ase2022y203.candidate.service.CandidateDTO;
import com.example.ase2022y203.candidate.service.CandidateService;
import com.example.ase2022y203.candidate.service.CandidateServiceImpl;
import com.example.ase2022y203.candidate.service.messages.CandidateListRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class GetCandidatesMockRepo {
    @Autowired
    private CandidateService candidateService;

    @MockBean
    private CandidateRepository candidateRepository;

    @Test
    public void shouldGetThreeCandidates() throws Exception {
        //Given the number of candidates added the system is three
        Candidate c1 = new Candidate(1, "Karen", "Long", "KL@gmail.com",
                "KLPass@123", "Admiral");
        Candidate c2 = new Candidate(2, "Michael", "Shell", "MS@gmail.com",
                "MSPass@123", "Alacrity");
        Candidate c3 = new Candidate(3, "Jerry", "Santos", "JS@gmail.com",
                "JSPass@123", "Sports4all");

        given(candidateRepository.getCandidates()).willReturn(List.of(c1, c2, c3));

        List<CandidateDTO> candidates;

        CandidateListRequest candidateListRequest = CandidateListRequest
                .of()
                .build();

        //When the candidates are retrieved
        var candidateListResponse = candidateService.getCandidates(candidateListRequest);

        //Then the size of the candidate list should be three
        assertEquals(3, candidateListResponse.getCandidates().size());
    }

    @Test
    public void shouldGetCandidateByID() throws Exception {
        //Given there are three candidates
        Candidate c1 = new Candidate(1, "Karen", "Long", "KL@gmail.com",
                "KLPass@123", "Admiral");
        Candidate c2 = new Candidate(2, "Michael", "Shell", "MS@gmail.com",
                "MSPass@123", "Alacrity");
        Candidate c3 = new Candidate(3, "Jerry", "Santos", "JS@gmail.com",
                "JSPass@123", "Sports4all");

        given(candidateRepository.getCandidateByID(1)).willReturn(Optional.of(c1));

        List<CandidateDTO> candidates;

        CandidateListRequest candidateListRequest = CandidateListRequest
                .of()
                .build();

        //When the candidate with the id of 1 is requested
        var candidateResponse = candidateService.getCandidateByID(1);


        //Then the candidate with id of 1 should be returned
        assertEquals("Karen", candidateResponse.get().getFirst_name());
    }

    @Test
    public void shouldGetAllCandidates() throws Exception {
        //Given that there are 2 candidates
        Candidate c1 = new Candidate(1, "Karen", "Long", "KL@gmail.com",
                "KLPass@123", "Admiral");
        Candidate c2 = new Candidate(2, "Michael", "Shell", "MS@gmail.com",
                "MSPass@123", "Alacrity");
        given(candidateRepository.getCandidates()).willReturn(List.of(c1, c2));
        List<CandidateDTO> candidates;
        CandidateListRequest candidateListRequest = CandidateListRequest
                .of()
                .build();
        //When the candidates are retrieved
        var candidateListResponse = candidateService.getCandidates(candidateListRequest);
        //Then the size of the candidate list should be 2
        assertEquals(2, candidateListResponse.getCandidates().size());
    }
}
