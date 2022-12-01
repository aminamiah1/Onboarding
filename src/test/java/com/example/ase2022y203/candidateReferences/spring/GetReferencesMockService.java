package com.example.ase2022y203.candidateReferences.spring;

import com.example.ase2022y203.candidateReferences.service.CandidateReferencesDTO;
import com.example.ase2022y203.candidateReferences.service.CandidateReferencesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GetReferencesMockService {

    @Autowired
    private CandidateReferencesService referencesService;

    @Test
    public void shouldGetTwoReferencesForCandidateTwo() throws Exception {
        //Given the number of candidate references, candidate one has is two
        List<CandidateReferencesDTO> candidateReferences = referencesService.getCandidateReferencesByCID(2);

        //When the references list is returned
        Integer numOfReferences = candidateReferences.size();

        //Then it should contain two references
        assertEquals(2, numOfReferences);
    }

    @Test
    public void shouldGetRightReferenceForCandidateOne() throws Exception {
        //Given the candidate references are retrieved for candidate one
        List<CandidateReferencesDTO> candidateReferences = referencesService.getCandidateReferencesByCID(1);

        //When reference one is returned
        CandidateReferencesDTO reference1 = candidateReferences.get(0);

        //Then reference one should contain the referee name "Angela Mooring"
        assertEquals("Angela Mooring", reference1.getReferee_name());
    }

}
