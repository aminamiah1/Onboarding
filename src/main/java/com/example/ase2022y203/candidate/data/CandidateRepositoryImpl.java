package com.example.ase2022y203.candidate.data;

import com.example.ase2022y203.candidate.domain.Candidate;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CandidateRepositoryImpl implements CandidateRepository {
    private final JdbcTemplate jdbc;
    private RowMapper<Candidate> candidateMapper;

    public CandidateRepositoryImpl(JdbcTemplate jdbcTemplate) {
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
        String allCandidatesSQL = "select * from candidates";
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
    public void save(Candidate newCandidate) {
        String addCandidateSQL = "INSERT INTO Candidates (id, first_name, surname, email, password, company_name) values (0, ?, ?, ?, ?, ?)";
            jdbc.update(addCandidateSQL, newCandidate.getId(), newCandidate.getFirst_name(), newCandidate.getSurname(),
                newCandidate.getEmail(), newCandidate.getPassword(), newCandidate.getCompany_name());
    }
}
