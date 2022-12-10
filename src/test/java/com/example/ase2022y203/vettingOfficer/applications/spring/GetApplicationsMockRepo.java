package com.example.ase2022y203.vettingOfficer.applications.spring;

import com.example.ase2022y203.applications.data.ApplicationsRepository;
import com.example.ase2022y203.applications.domain.Applications;
import com.example.ase2022y203.applications.service.ApplicationsDTO;
import com.example.ase2022y203.applications.service.ApplicationsService;
import com.example.ase2022y203.candidate.domain.Candidate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class GetApplicationsMockRepo {
    @MockBean
    private ApplicationsRepository applicationsRepository;

    @Autowired
    private ApplicationsService applicationsService;

    @Test
    public void shouldGetThreeApplications() throws Exception {
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

        given(applicationsRepository.getApplications()).willReturn(List.of(a1, a2, a3));

        List<ApplicationsDTO> applications;

        //When the applications are retrieved
        var applicationsResponse = applicationsRepository.getApplications();

        //Then the size of the applications list should be three
        assertEquals(3, applicationsResponse.size());
    }

    @Test
    public void shouldGetApplicationByID() throws Exception {
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

        given(applicationsRepository.findApplicationById(Optional.of(3))).willReturn(Optional.of(a3));

        //When the application with an id of 3 is retrieved
        Optional<Applications> applicationsResponse = applicationsRepository.findApplicationById(Optional.of(3));

        //Then the application status of application 3 should be denied
        assertEquals("denied", applicationsResponse.get().getAppstatus());
    }
}
