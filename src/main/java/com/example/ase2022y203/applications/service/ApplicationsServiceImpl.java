package com.example.ase2022y203.applications.service;

import com.example.ase2022y203.applications.data.ApplicationsRepository;
import com.example.ase2022y203.applications.domain.Applications;
import com.example.ase2022y203.applications.service.messages.ApplicationsListRequest;
import com.example.ase2022y203.applications.service.messages.ApplicationsListResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    private List<ApplicationsDTO> getPendingApplications(){
        List<Applications> applications = applicationsRepository.getPendingApplications();
        return ApplicationsAssembler.toDto(applications);
    }

    private List<ApplicationsDTO> getDeniedApplications(){
        List<Applications> applications = applicationsRepository.getDeniedApplications();
        return ApplicationsAssembler.toDto(applications);
    }

    private List<ApplicationsDTO> getApprovedApplications(){
        List<Applications> applications = applicationsRepository.getApprovedApplications();
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
        applications = getPendingApplications();
        return ApplicationsListResponse
                .of()
                .request(applicationsListRequest)
                .applications(applications)
                .build();
    }

    @Override
    public ApplicationsListResponse getDeniedApplications(ApplicationsListRequest applicationsListRequest) {
        List<ApplicationsDTO> applications;
        applications = getDeniedApplications();
        return ApplicationsListResponse
                .of()
                .request(applicationsListRequest)
                .applications(applications)
                .build();
    }

    @Override
    public ApplicationsListResponse getApprovedApplications(ApplicationsListRequest applicationsListRequest) {
        List<ApplicationsDTO> applications;
        applications = getApprovedApplications();
        return ApplicationsListResponse
                .of()
                .request(applicationsListRequest)
                .applications(applications)
                .build();
    }

    @Override
    public Optional<ApplicationsDTO> getApplicationByID(Optional<Integer> id) {
        Optional<Applications> aApplication = applicationsRepository.findApplicationById(id);
        if(aApplication.isPresent()){
            return Optional.of(ApplicationsAssembler.toDto(aApplication.get()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void save(ApplicationsDTO applicationsDTO) {
        Applications newApplications = new Applications(
                applicationsDTO.getId(),
                applicationsDTO.getAppstatus(),
                applicationsDTO.getCid()
        );
        applicationsRepository.save(newApplications);
    }

    @Override
    public void updateStatus(ApplicationsDTO applicationsDTO) {
        Applications newApplications = new Applications(
                applicationsDTO.getId(),
                applicationsDTO.getAppstatus(),
                applicationsDTO.getCid()
        );
        applicationsRepository.updateStatus(newApplications);
    }

    @Override
    public void delete(ApplicationsDTO applicationsDTO) {
        Applications deleteApplication = new Applications(
                applicationsDTO.getId(),
                applicationsDTO.getAppstatus(),
                applicationsDTO.getCid()
        );
        applicationsRepository.delete(deleteApplication);
    }

}
