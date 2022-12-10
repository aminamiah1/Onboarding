package com.example.ase2022y203.vettingOfficers.data;

import com.example.ase2022y203.vettingOfficers.domain.VettingOfficers;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VetOfficerRepositoryImpl implements VetOfficerRepository {
    private VetOfficerRepoJDBC vetOfficerRepoJDBC;
    private final JdbcTemplate jdbc;
    private RowMapper<VettingOfficers> candidateMapper;

    public VetOfficerRepositoryImpl(VetOfficerRepoJDBC aRepo, JdbcTemplate jdbcTemplate) {
        vetOfficerRepoJDBC = aRepo;
        jdbc = jdbcTemplate;
        setCandidateMapper();
    }

    private void setCandidateMapper() {
        candidateMapper = (rs, i) -> new VettingOfficers(
                rs.getInt("id"),
                rs.getString("first_name"),
                rs.getString("surname"),
                rs.getString("email"),
                rs.getString("password")
        );
    }

    @Override
    public Optional<VettingOfficers> getVettingOfficerByEmail(String email) {
        return vetOfficerRepoJDBC.findVettingOfficersByEmail(email);
    }

    @Override
    public Optional<VettingOfficers> getVettingOfficerById(Optional<Integer> id) {
        return vetOfficerRepoJDBC.findVettingOfficersById(id);
    }

    @Override
    public List<VettingOfficers> getOfficers() {
        String allOfficersSQl = "select * from Vetting_Officers";
        return jdbc.query(allOfficersSQl, candidateMapper);
    }

    @Override
    public void save(VettingOfficers newAdmin) {
        String addOfficerSQL = "INSERT INTO Vetting_Officers (first_name, surname, email, password) values (?, ?, ?, ?)";
        jdbc.update(addOfficerSQL, newAdmin.getFirst_name(), newAdmin.getSurname(),
                newAdmin.getEmail(), newAdmin.getPassword());
    }

    @Override
    public void delete(VettingOfficers vettingOfficers) {
        String deleteQuery = "DELETE FROM Vetting_Officers WHERE ID = ?";
        jdbc.update(deleteQuery, vettingOfficers.getId());
    }
}
