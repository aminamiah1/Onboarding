package com.example.ase2022y203.applications.data;

import com.example.ase2022y203.applications.domain.Applications;

import java.util.List;

public interface ApplicationsRepository {
    List<Applications> getApplications();
    List<Applications> getPendingApplications();
    List<Applications> getDeniedApplications();
    List<Applications> getApprovedApplications();
}
