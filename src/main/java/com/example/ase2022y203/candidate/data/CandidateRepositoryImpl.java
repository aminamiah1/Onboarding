package com.example.ase2022y203.candidate.data;

import com.example.ase2022y203.candidate.domain.Candidate;
import com.example.ase2022y203.candidatePersonal.data.CandidatePersonalRepoJDBC;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class CandidateRepositoryImpl implements CandidateRepository {
    private final JdbcTemplate jdbc;
    private RowMapper<Candidate> candidateMapper;

    private CandidateRepoJDBC repoJDBC;
    public CandidateRepositoryImpl(JdbcTemplate jdbcTemplate, CandidateRepoJDBC aRepo) {
        repoJDBC = aRepo;
        jdbc = jdbcTemplate;
        setCandidateMapper();
    }

    private void setCandidateMapper() {
        candidateMapper = (rs, i) -> new Candidate(
                rs.getInt("id"),
                rs.getString("first_name"),
                rs.getString("surname"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("company_name")
        );
    }

    public List<Candidate> getCandidates() {
        String allCandidatesSQL = "select * from Candidates";
        return jdbc.query(allCandidatesSQL, candidateMapper);
    }

    public List<Candidate> getAllCandidates() {
        String allCandidatesSQL = "select * from Candidates";
        return jdbc.query(allCandidatesSQL, candidateMapper);
    }
    @Override
    public Optional<Candidate> getCandidateByID(Integer id) {
        String candidateByIDSql = "select * from candidates where ID = ?";

        Optional<Candidate> theCandidate;

        try {
            theCandidate = Optional.of(jdbc.queryForObject(candidateByIDSql, candidateMapper, id));
            return theCandidate;
        } catch (IncorrectResultSizeDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Candidate> getCandidateByEmail(String email) {
        String candidateByEmailSql = "select * from candidates where email = ?";

        Optional<Candidate> theCandidate;

        try {
            theCandidate = Optional.of(jdbc.queryForObject(candidateByEmailSql, candidateMapper, email));
            return theCandidate;
        } catch (IncorrectResultSizeDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(Candidate newCandidate) {
        String addCandidateSQL = "INSERT INTO Candidates (first_name, surname, email, password, company_name) values (?, ?, ?, ?, ?)";
            jdbc.update(addCandidateSQL, newCandidate.getFirst_name(), newCandidate.getSurname(),
                newCandidate.getEmail(), newCandidate.getPassword(), newCandidate.getCompany_name());
        String addCandidatePersonalSQL = "INSERT INTO Personal_Information (cid) values (?)";
            jdbc.update(addCandidatePersonalSQL, newCandidate.getId());
    }
}
