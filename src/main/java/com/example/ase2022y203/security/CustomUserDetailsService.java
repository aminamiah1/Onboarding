package com.example.ase2022y203.security;

import com.example.ase2022y203.candidate.data.CandidateRepository;
import com.example.ase2022y203.candidate.domain.Candidate;
import com.example.ase2022y203.masterAdmin.data.MasterAdminRepository;
import com.example.ase2022y203.masterAdmin.domain.MasterAdmin;
import com.example.ase2022y203.vettingOfficers.data.VetOfficerRepository;
import com.example.ase2022y203.vettingOfficers.domain.VettingOfficers;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
    private MasterAdminRepository masterAdminRepository;

    private VetOfficerRepository vetOfficerRepository;

    public CustomUserDetailsService(CandidateRepository aCandidateRepo, MasterAdminRepository aMasterRepo, VetOfficerRepository aVetOfficerRepo){
        this.candidateRepository = aCandidateRepo;
        this.masterAdminRepository = aMasterRepo;
        this.vetOfficerRepository = aVetOfficerRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Candidate> user = candidateRepository.getCandidateByEmail(email);
        Optional<VettingOfficers> vettingOfficer = vetOfficerRepository.getVettingOfficerByEmail(email);
        Optional<MasterAdmin> masterAdmin = masterAdminRepository.getMasterAdminByEmail(email);
        if(user.isPresent()){
            return new org.springframework.security.core.userdetails.User(user.get().getEmail()
                    , user.get().getPassword()
                    , getAuthorities("ROLE_USER"));

        } else if (masterAdmin.isPresent()){
            return new org.springframework.security.core.userdetails.User(masterAdmin.get().getEmail()
                    , masterAdmin.get().getPassword()
                    , getAuthorities("ROLE_ADMIN"));
        } else  if (vettingOfficer.isPresent()){
            return new org.springframework.security.core.userdetails.User(vettingOfficer.get().getEmail()
                    , vettingOfficer.get().getPassword()
                    , getAuthorities("ROLE_OFFICER"));
        } else {
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Arrays.asList(new SimpleGrantedAuthority(role));
    }

}
