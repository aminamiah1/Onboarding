package com.example.ase2022y203.candidateReferences.unit;

import com.example.ase2022y203.candidate.data.CandidateRepository;
import com.example.ase2022y203.candidate.domain.Candidate;
import com.example.ase2022y203.candidateReferences.data.CandidateReferencesRepository;
import com.example.ase2022y203.candidateReferences.domain.CandidateReferences;
import com.example.ase2022y203.candidateReferences.service.CandidateReferencesDTO;
import com.example.ase2022y203.candidateReferences.service.CandidateReferencesService;
import com.example.ase2022y203.candidateReferences.service.CandidateReferencesServiceImpl;
import com.example.ase2022y203.candidateReferences.service.messages.CandidateRefListRequest;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class GetReferences {

    private CandidateReferencesService referencesService;

    private CandidateReferencesRepository referencesRepo;

    @Test
    public void shouldGetFourReferences() throws Exception {
        //Given there are two candidates with two references each
        Candidate c1 = new Candidate(1, "Karen", "Long", "KL@gmail.com",
                "KLPass@123", "Admiral");

        CandidateReferences c1r1 = new CandidateReferences(1, 1, "Thomas Middlebrow"
                , "+445678976543");

        CandidateReferences c1r2 = new CandidateReferences(2, 1, "Hilary Nook"
                , "+445634976543");

        Candidate c2 = new Candidate(2, "Poppy", "Close", "PC@gmail.com",
                "PCPass@123", "Starbucks");

        CandidateReferences c2r1 = new CandidateReferences(3, 2, "Ralph Jones"
                , "+445678436543");

        CandidateReferences c2r2 = new CandidateReferences(4, 2, "Taylor Murray"
                , "+445678466543");
        
        //When both of the candidates references are added
        referencesRepo = mock(CandidateReferencesRepository.class);
        referencesService = mock(CandidateReferencesService.class);
        
        given(referencesRepo.getCandidateReferencesByCID(1)).willReturn(List.of(c1r1, c1r2));
        given(referencesRepo.getCandidateReferencesByCID(2)).willReturn(List.of(c2r1, c2r2));
        
        referencesService = new CandidateReferencesServiceImpl(referencesRepo);
        
        List<CandidateReferencesDTO> referencesForCandidateOne = referencesService.getCandidateReferencesByCID(1);
        List<CandidateReferencesDTO> referencesForCandidateTwo = referencesService.getCandidateReferencesByCID(2);

        //Then the lists added together should contain four references
        List<CandidateReferencesDTO> referencesResponse = Stream
                .concat(referencesForCandidateOne.stream(), referencesForCandidateTwo.stream()).toList();

        assertEquals(4, referencesResponse.size());
    }

    @Test
    public void shouldGetRightCandidateIDFromReference() throws Exception{
        //Given there are two candidates with two references each
        Candidate c1 = new Candidate(1, "Karen", "Long", "KL@gmail.com",
                "KLPass@123", "Admiral");

        CandidateReferences c1r1 = new CandidateReferences(1, 1, "Thomas Middlebrow"
                , "+445678976543");

        CandidateReferences c1r2 = new CandidateReferences(2, 1, "Hilary Nook"
                , "+445634976543");

        Candidate c2 = new Candidate(2, "Poppy", "Close", "PC@gmail.com",
                "PCPass@123", "Starbucks");

        CandidateReferences c2r1 = new CandidateReferences(3, 2, "Ralph Jones"
                , "+445678436543");

        CandidateReferences c2r2 = new CandidateReferences(4, 2, "Taylor Murray"
                , "+445678466543");

        //When both of the candidates references are added
        referencesRepo = mock(CandidateReferencesRepository.class);
        referencesService = mock(CandidateReferencesService.class);

        given(referencesRepo.getCandidateReferencesByCID(1)).willReturn(List.of(c1r1, c1r2));
        given(referencesRepo.getCandidateReferencesByCID(2)).willReturn(List.of(c2r1, c2r2));

        referencesService = new CandidateReferencesServiceImpl(referencesRepo);

        List<CandidateReferencesDTO> referencesForCandidateOne = referencesService.getCandidateReferencesByCID(1);

        //Then reference one for candidate one should contain a candidate id of 1
        assertEquals(1, referencesForCandidateOne.get(0).getC_id());
    }
}
