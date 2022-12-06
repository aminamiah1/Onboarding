package com.example.ase2022y203.candidatesPersonal.spring;



import com.example.ase2022y203.candidatePersonal.web.forms.CandidatePersonalForm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ValidationTestSuite {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @Test
    public void incorrectNINumberShouldReturnViolation() {

        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();

        validatorFactory.close();

        //Given the candidate gives a national insurance number in the wrong format
        CandidatePersonalForm candidatePersonalForm = new CandidatePersonalForm();
        candidatePersonalForm.setAge(20);
        candidatePersonalForm.setNational_insurance("678987");

        //When they submit the form
        Set<ConstraintViolation<CandidatePersonalForm>> violations = validator.validate(candidatePersonalForm);

        //Then there should be violations
        assertFalse(violations.isEmpty());
    }

    @Test
    public void incorrectAgeShouldReturnViolation() {

        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();

        validatorFactory.close();

        //Given the candidate gives their age in the wrong format
        CandidatePersonalForm candidatePersonalForm = new CandidatePersonalForm();
        candidatePersonalForm.setNational_insurance("QQ567893A");
        candidatePersonalForm.setAge(140);

        //When they submit the form
        Set<ConstraintViolation<CandidatePersonalForm>> violations = validator.validate(candidatePersonalForm);

        //Then there should be violations
        assertFalse(violations.isEmpty());
    }

    @Test
    public void correctNINumberAndAgeShouldNotReturnViolations() {

        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();

        validatorFactory.close();

        //Given the candidate gives the age and national insurance number in the correct format
        CandidatePersonalForm candidatePersonalForm = new CandidatePersonalForm();
        candidatePersonalForm.setAge(20);
        candidatePersonalForm.setNational_insurance("QQ567893A");
        candidatePersonalForm.setTelephone_number("+442344342423");

        //When they submit the form
        Set<ConstraintViolation<CandidatePersonalForm>> violations = validator.validate(candidatePersonalForm);

        //Then there should be no violations
        assertTrue(violations.isEmpty());
    }

    @Test
    public void emptyGenderAndTelephoneNumberAndEthnicityShouldReturnViolations() {

        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();

        validatorFactory.close();

        //Given the candidate provides empty data
        CandidatePersonalForm candidatePersonalForm = new CandidatePersonalForm();
        candidatePersonalForm.setAge(20);
        candidatePersonalForm.setNational_insurance("QQ567893A");
        candidatePersonalForm.setGender("");
        candidatePersonalForm.setTelephone_number("");
        candidatePersonalForm.setEthnicity("");

        //When they submit the form
        Set<ConstraintViolation<CandidatePersonalForm>> violations = validator.validate(candidatePersonalForm);

        //Then there should be violations
        assertFalse(violations.isEmpty());
    }

    @Test
    public void correctFormDataShouldNotReturnViolations() {

        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();

        validatorFactory.close();

        //Given the candidate provides all correct data
        CandidatePersonalForm candidatePersonalForm = new CandidatePersonalForm();
        candidatePersonalForm.setAge(20);
        candidatePersonalForm.setNational_insurance("QQ567893A");
        candidatePersonalForm.setGender("Male");
        candidatePersonalForm.setEthnicity("White");
        candidatePersonalForm.setTelephone_number("+441314960624");

        //When they submit the form
        Set<ConstraintViolation<CandidatePersonalForm>> violations = validator.validate(candidatePersonalForm);

        //Then there should be no violations
        assertTrue(violations.isEmpty());
    }

}
