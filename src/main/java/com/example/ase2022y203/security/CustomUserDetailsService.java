package com.example.ase2022y203.security;

import com.example.ase2022y203.candidate.data.CandidateRepository;
import com.example.ase2022y203.candidate.domain.Candidate;
import com.example.ase2022y203.candidate.service.CandidateService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

    private CandidateRepository candidateRepository;

    public CustomUserDetailsService(CandidateRepository aCandidateRepo){
        this.candidateRepository = aCandidateRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Candidate> user = candidateRepository.getCandidateByEmail(email);
        if(user.isPresent()){
            return new org.springframework.security.core.userdetails.User(user.get().getEmail()
                    , user.get().getPassword()
                    , getAuthorities("USER"));
        } else{
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Arrays.asList(new SimpleGrantedAuthority(role));
    }

}
