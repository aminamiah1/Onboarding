package com.example.ase2022y203.candidate.web;

import com.example.ase2022y203.candidate.service.CandidateService;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
public class UniqueIdValidator implements ConstraintValidator<UniqueId, Integer> {
    private final CandidateService candidateService;
    public UniqueIdValidator(CandidateService aCandidateService){
        candidateService = aCandidateService;
    }
    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {
        return !candidateService.getCandidateByID(id).isPresent();
    }
}