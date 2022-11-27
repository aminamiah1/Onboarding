package com.example.ase2022y203.candidatePersonal.web.forms.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NINumberFormatValidator implements ConstraintValidator<NationalInsuranceFormat, String> {
    @Override
    public void initialize(NationalInsuranceFormat NI){
    }
    
    @Override
    public boolean isValid(String NI, ConstraintValidatorContext cxt){
        return NI != null && NI.matches("^[a-zA-Z]{2}[0-9]{6}[a-zA-Z]{1}$")
                && (NI.length() == 9);
    }
}
