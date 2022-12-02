package com.example.ase2022y203.candidateReferences.spring;

import com.example.ase2022y203.candidate.domain.Candidate;
import com.example.ase2022y203.candidateReferences.data.CandidateReferencesRepository;
import com.example.ase2022y203.candidateReferences.domain.CandidateReferences;
import com.example.ase2022y203.candidateReferences.service.CandidateReferencesDTO;
import com.example.ase2022y203.candidateReferences.service.CandidateReferencesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class EditReferencesMockRepo {
    @Autowired
    private CandidateReferencesService referencesService;

    @MockBean
    private CandidateReferencesRepository referencesRepository;

    @Test
    public void editedRefereeNameShouldBeChanged() throws Exception{
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

        //When the references are retrieved and a referee name is changed
        List<CandidateReferences> candidateReferences;

        candidateReferences = referencesRepository.getCandidateReferencesByCID(1);

        CandidateReferences editReference = candidateReferences.get(1);

        //Mock database edit
        editReference.setReferee_name("Michael Kelly");

        //Then the referee name should be changed
        assertEquals("Michael Kelly", editReference.getReferee_name());
    }

    @Test
    public void editedReferenceNumberShouldBeChanged() throws Exception{
        //Given a candidate has two references
        Candidate c1 = new Candidate(1, "Larry", "Loop", "LL@gmail.com", "LLPass@123",
                "Sports Direct");

        CandidateReferences cr1 = new CandidateReferences(1, 1, "Sarah Brighton",
                "+447568765432");

        CandidateReferences cr2 = new CandidateReferences(2, 1, "Michael Connelly",
                "+447568742332");

        given(referencesRepository.getCandidateReferencesByCID(1)).willReturn(List.of(cr1, cr2));

        //When reference two's phone number is changed
        List<CandidateReferences> candidateReferences;

        candidateReferences = referencesRepository.getCandidateReferencesByCID(1);

        CandidateReferences editReference = candidateReferences.get(1);

        //Mock database edit
        editReference.setReferee_phone_number("+443456654324");

        //Then reference two's phone number should be changed
        assertEquals(editReference.getReferee_phone_number(), "+443456654324");
    }
}
