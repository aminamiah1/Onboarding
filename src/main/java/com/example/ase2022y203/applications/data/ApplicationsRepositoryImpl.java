package com.example.ase2022y203.applications.data;

import com.example.ase2022y203.applications.domain.Applications;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ApplicationsRepositoryImpl implements  ApplicationsRepository {
    private final ApplicationsRepoJDBC applicationsRepoJDBC;

    public ApplicationsRepositoryImpl(ApplicationsRepoJDBC aRepo) {
        this.applicationsRepoJDBC = aRepo;
    }

    @Override
    public List<Applications> getApplications() {
        return applicationsRepoJDBC.findAll();
    }

    @Override
    public List<Applications> getPendingApplications() {
        return applicationsRepoJDBC.findAllByAppstatusLike("pending");
    }

    @Override
    public List<Applications> getDeniedApplications() {
        return applicationsRepoJDBC.findAllByAppstatusLike("denied");
    }

    @Override
    public List<Applications> getApprovedApplications() {
        return applicationsRepoJDBC.findAllByAppstatusLike("approved");
    }

    @Override
    public Optional<Applications> findApplicationById(Optional<Integer> id) {
        return applicationsRepoJDBC.findById(id.get());
    }
}
