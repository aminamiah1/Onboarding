package com.example.ase2022y203.applications.service;

import com.example.ase2022y203.applications.service.messages.ApplicationsListRequest;
import com.example.ase2022y203.applications.service.messages.ApplicationsListResponse;

public interface ApplicationsService {
    ApplicationsListResponse getApplications(ApplicationsListRequest applicationsListRequest);
}
