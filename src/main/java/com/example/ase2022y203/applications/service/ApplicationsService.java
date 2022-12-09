package com.example.ase2022y203.applications.service;

import com.example.ase2022y203.applications.service.messages.ApplicationsListRequest;
import com.example.ase2022y203.applications.service.messages.ApplicationsListResponse;

import java.util.Optional;

public interface ApplicationsService {
    ApplicationsListResponse getApplications(ApplicationsListRequest applicationsListRequest);
    ApplicationsListResponse getPendingApplications(ApplicationsListRequest applicationsListRequest);
    ApplicationsListResponse getDeniedApplications(ApplicationsListRequest applicationsListRequest);
    ApplicationsListResponse getApprovedApplications(ApplicationsListRequest applicationsListRequest);
    Optional<ApplicationsDTO> getApplicationByID(Optional<Integer> id);
    void save(ApplicationsDTO applicationsDTO);
    void updateStatus(ApplicationsDTO applicationsDTO);
}
