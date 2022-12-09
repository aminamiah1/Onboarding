package com.example.ase2022y203.applications.service;

import com.example.ase2022y203.applications.data.ApplicationsRepository;
import com.example.ase2022y203.applications.domain.Applications;
import com.example.ase2022y203.applications.service.messages.ApplicationsListRequest;
import com.example.ase2022y203.applications.service.messages.ApplicationsListResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationsServiceImpl implements ApplicationsService {
    private final ApplicationsRepository applicationsRepository;

    public ApplicationsServiceImpl(ApplicationsRepository applicationsRepository) {
        this.applicationsRepository = applicationsRepository;
    }

    private List<ApplicationsDTO> getApplications(){
        List<Applications> applications = applicationsRepository.getApplications();
        return ApplicationsAssembler.toDto(applications);
    }

    private List<ApplicationsDTO> getPendingAppliations(){
        List<Applications> applications = applicationsRepository.getPendingApplications();
        return ApplicationsAssembler.toDto(applications);
    }

    @Override
    public ApplicationsListResponse getApplications(ApplicationsListRequest applicationsListRequest) {
        List<ApplicationsDTO> applications;
        applications = getApplications();
        return ApplicationsListResponse
                .of()
                .request(applicationsListRequest)
                .applications(applications)
                .build();
    }

    @Override
    public ApplicationsListResponse getPendingApplications(ApplicationsListRequest applicationsListRequest) {
        List<ApplicationsDTO> applications;
        applications = getPendingAppliations();
        return ApplicationsListResponse
                .of()
                .request(applicationsListRequest)
                .applications(applications)
                .build();
    }
}
