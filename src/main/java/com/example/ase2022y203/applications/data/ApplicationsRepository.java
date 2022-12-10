package com.example.ase2022y203.applications.data;

import com.example.ase2022y203.applications.domain.Applications;

import java.util.List;
import java.util.Optional;

public interface ApplicationsRepository {
    List<Applications> getApplications();
    List<Applications> getPendingApplications();
    List<Applications> getDeniedApplications();
    List<Applications> getApprovedApplications();
    Optional<Applications> findApplicationById(Optional<Integer> id);
    void save(Applications newApplications);
    void updateStatus(Applications newApplications);
    void delete(Applications newApplications);
}
