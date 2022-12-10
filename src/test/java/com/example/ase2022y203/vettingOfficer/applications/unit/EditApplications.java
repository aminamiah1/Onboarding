package com.example.ase2022y203.vettingOfficer.applications.unit;


import com.example.ase2022y203.applications.data.ApplicationsRepository;
import com.example.ase2022y203.applications.data.ApplicationsRepositoryImpl;
import com.example.ase2022y203.applications.domain.Applications;
import com.example.ase2022y203.applications.service.ApplicationsDTO;
import com.example.ase2022y203.applications.service.ApplicationsService;
import com.example.ase2022y203.candidate.domain.Candidate;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class EditApplications {

    private ApplicationsService applicationsService;
    private ApplicationsRepository applicationsRepository;

    @Test
    public void shouldGetEditedStatusApproved() throws Exception {
        //Given the number of applications for all three candidates is three
        Candidate c1 = new Candidate(1, "Karen", "Long", "KL@gmail.com",
                "KLPass@123", "Admiral");
        Candidate c2 = new Candidate(2, "Michael", "Shell", "MS@gmail.com",
                "MSPass@123", "Alacrity");
        Candidate c3 = new Candidate(3, "Jerry", "Santos", "JS@gmail.com",
                "JSPass@123", "Sports4all");

        Applications a1 = new Applications(1, "pending", c1);
        Applications a2 = new Applications(2, "approved", c2);
        Applications a3 = new Applications(3, "denied", c3);

        applicationsRepository = mock(ApplicationsRepositoryImpl.class);

        given(applicationsRepository.getApplications()).willReturn(List.of(a1, a2, a3));

        //When the application 1 is edited and the applications are retrieved
        a1.setAppstatus("approved");
        var applicationsResponse = applicationsRepository.getApplications();

        //Then the status should be edited for application 1
        assertEquals("approved", applicationsResponse.get(1).getAppstatus());
    }

    @Test
    public void shouldGetEditedCandidateForApplication() throws Exception {
        //Given the number of applications for all three candidates is three
        Candidate c1 = new Candidate(1, "Karen", "Long", "KL@gmail.com",
                "KLPass@123", "Admiral");
        Candidate c2 = new Candidate(2, "Michael", "Shell", "MS@gmail.com",
                "MSPass@123", "Alacrity");
        Candidate c3 = new Candidate(3, "Jerry", "Santos", "JS@gmail.com",
                "JSPass@123", "Sports4all");

        Applications a1 = new Applications(1, "pending", c1);
        Applications a2 = new Applications(2, "approved", c2);
        Applications a3 = new Applications(3, "denied", c3);

        applicationsRepository = mock(ApplicationsRepositoryImpl.class);

        given(applicationsRepository.getApplications()).willReturn(List.of(a1, a2, a3));

        //When the application 1 is edited to change the candidate and the applications are retrieved
        a1.setCid(c2);
        var applicationsResponse = applicationsRepository.getApplications();

        //Then the first name for the candidate retrieved from application 1 should be Michael
        assertEquals("Michael", applicationsResponse.get(1).getCid().getFirst_name());
    }
}
