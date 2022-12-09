package com.example.ase2022y203.applications.data;

import com.example.ase2022y203.applications.domain.Applications;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationsRepoJDBC extends CrudRepository<Applications, Integer> {
    List<Applications> findAll();
    List<Applications> findAllByAppstatusLike(String like);
    Optional<Applications> findById(Integer id);
}
