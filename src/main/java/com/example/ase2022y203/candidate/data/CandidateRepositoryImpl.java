package com.example.ase2022y203.candidate.data;

import com.example.ase2022y203.candidate.domain.Candidate;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

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
                rs.getString("firstName"),
                rs.getString("surname"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("companyName")
        );
    }

    public List<Candidate> getCandidates() {
        String allCandidatesSQL = "select * from candidates";
        return jdbc.query(allCandidatesSQL, candidateMapper);
    }
}
