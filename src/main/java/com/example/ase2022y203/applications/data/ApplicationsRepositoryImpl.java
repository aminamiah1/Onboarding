package com.example.ase2022y203.applications.data;

import com.example.ase2022y203.applications.domain.Applications;
import org.springframework.stereotype.Repository;

import java.util.List;

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

}
