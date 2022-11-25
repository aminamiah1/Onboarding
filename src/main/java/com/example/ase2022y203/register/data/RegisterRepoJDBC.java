package com.example.ase2022y203.register.data;

import com.example.ase2022y203.register.domain.Register;
import org.springframework.data.repository.CrudRepository;

public interface RegisterRepoJDBC extends CrudRepository<Register, Integer> {
    Iterable<Register> findAll();
}
