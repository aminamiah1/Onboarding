package com.example.ase2022y203.candidateReferences.spring;

import com.example.ase2022y203.candidate.domain.Candidate;
import com.example.ase2022y203.candidateReferences.data.CandidateReferencesRepository;
import com.example.ase2022y203.candidateReferences.domain.CandidateReferences;
import com.example.ase2022y203.candidateReferences.service.CandidateReferencesDTO;
import com.example.ase2022y203.candidateReferences.service.CandidateReferencesService;
import com.example.ase2022y203.candidateReferences.service.messages.CandidateRefListRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class GetReferencesMockRepo {
    @Autowired
    private CandidateReferencesService referencesService;

    @MockBean
    private CandidateReferencesRepository referencesRepository;

    @Test
    public void shouldGetThreeReferencesForCandidate() throws Exception{
           //Given a candidate has three references
           Candidate c1 = new Candidate(1, "Larry", "Loop", "LL@gmail.com", "LLPass@123",
                    "Sports Direct");

           CandidateReferences cr1 = new CandidateReferences(1, 1, "Sarah Brighton",
                   "+447568765432");

           CandidateReferences cr2 = new CandidateReferences(2, 1, "Michael Connelly",
                "+447568742332");

           CandidateReferences cr3 = new CandidateReferences(3, 1, "David Pop",
                "+447564365432");

           given(referencesRepository.getCandidateReferencesByCID(1)).willReturn(List.of(cr1, cr2, cr3));

           //When the references are retrieved
           List<CandidateReferencesDTO> candidateReferences;

           candidateReferences = referencesService.getCandidateReferencesByCID(1);

           //Then the number of references should be three
           assertEquals(3, candidateReferences.size());
    }

    @Test
    public void shouldGetReferenceTwoForACandidate() throws Exception{
        //Given a candidate has two references
        Candidate c1 = new Candidate(1, "Larry", "Loop", "LL@gmail.com", "LLPass@123",
                "Sports Direct");

        CandidateReferences cr1 = new CandidateReferences(1, 1, "Sarah Brighton",
                "+447568765432");

        CandidateReferences cr2 = new CandidateReferences(2, 1, "Michael Connelly",
                "+447568742332");

        //When the references are retrieved
        given(referencesRepository.getCandidateReferencesByCID(1)).willReturn(List.of(cr1, cr2));

        List<CandidateReferencesDTO> candidateReferences;

        candidateReferences = referencesService.getCandidateReferencesByCID(1);

        //Then reference two should contain the phone number +447568765432
        assertEquals("+447568742332", candidateReferences.get(1).getReferee_phone_number());
    }
}
