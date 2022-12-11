package com.example.ase2022y203.applications.data;

import com.example.ase2022y203.applications.domain.Applications;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ApplicationsRepositoryImpl implements  ApplicationsRepository {
    private final JdbcTemplate jdbc;
    private final ApplicationsRepoJDBC applicationsRepoJDBC;

    public ApplicationsRepositoryImpl(JdbcTemplate jdbc, ApplicationsRepoJDBC aRepo) {
        this.jdbc = jdbc;
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

    @Override
    public void save(Applications newApplications) {
        applicationsRepoJDBC.save(newApplications);
    }

    @Override
    public void updateStatus(Applications newApplications) {
        String updateQuery = "update Applications set App_Status = ? where id = ?";
        jdbc.update(updateQuery, newApplications.getAppstatus(), newApplications.getId());
    }

    @Override
    public void delete(Applications newApplications) {
        String deleteQuery = "DELETE FROM Applications WHERE ID = ?";
        jdbc.update(deleteQuery, newApplications.getId());
    }
}
