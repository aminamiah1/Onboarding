package com.example.ase2022y203.vettingOfficer.applications.spring;

import com.example.ase2022y203.applications.service.ApplicationsDTO;
import com.example.ase2022y203.applications.service.ApplicationsService;
import com.example.ase2022y203.applications.service.messages.ApplicationsListRequest;
import com.example.ase2022y203.applications.service.messages.ApplicationsListResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GetApplicationsRealRepo {
    @Autowired
    private ApplicationsService applicationsService;

    @Test
    public void shouldGetFiveApplications() throws Exception {
        //Given the number of applications in the system is five
        ApplicationsListRequest applicationsListRequest = ApplicationsListRequest
                .of()
                .build();

       var applicationsResponse = applicationsService.getApplications(applicationsListRequest);

        assertEquals(5, applicationsResponse.getApplications().size());
    }

    @Test
    public void shouldGetKerry() throws Exception {
        //Given a candidate called kerry is attached to an application
        //When the application with the id of 1 is returned
        var applicationsResponse = applicationsService.getApplicationByID(Optional.of(1));

        //Then the application retrieved should be linked to a candidate called Kerry
        assertEquals("Kerry", applicationsResponse.get().getCid().getFirst_name());
    }
}
