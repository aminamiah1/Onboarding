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

        CandidatePersonalForm candidatePersonalForm = new CandidatePersonalForm();
        candidatePersonalForm.setAge(20);
        candidatePersonalForm.setNational_insurance("678987");

        Set<ConstraintViolation<CandidatePersonalForm>> violations = validator.validate(candidatePersonalForm);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void incorrectAgeShouldReturnViolation() {

        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();

        validatorFactory.close();

        CandidatePersonalForm candidatePersonalForm = new CandidatePersonalForm();
        candidatePersonalForm.setNational_insurance("QQ567893A");
        candidatePersonalForm.setAge(140);

        Set<ConstraintViolation<CandidatePersonalForm>> violations = validator.validate(candidatePersonalForm);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void correctNINumberAndAgeShouldNotReturnViolations() {

        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();

        validatorFactory.close();

        CandidatePersonalForm candidatePersonalForm = new CandidatePersonalForm();
        candidatePersonalForm.setAge(20);
        candidatePersonalForm.setNational_insurance("QQ567893A");

        Set<ConstraintViolation<CandidatePersonalForm>> violations = validator.validate(candidatePersonalForm);

        assertTrue(violations.isEmpty());
    }

    @Test
    public void emptyGenderAndSexualityAndEthnicityShouldReturnViolations() {

        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();

        validatorFactory.close();

        CandidatePersonalForm candidatePersonalForm = new CandidatePersonalForm();
        candidatePersonalForm.setAge(20);
        candidatePersonalForm.setNational_insurance("QQ567893A");
        candidatePersonalForm.setGender("");
        candidatePersonalForm.setSexuality("");
        candidatePersonalForm.setEthnicity("");

        Set<ConstraintViolation<CandidatePersonalForm>> violations = validator.validate(candidatePersonalForm);

        assertFalse(violations.isEmpty());
    }

    @Test
    public void correctFormDataShouldNotReturnViolations() {

        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();

        validatorFactory.close();

        CandidatePersonalForm candidatePersonalForm = new CandidatePersonalForm();
        candidatePersonalForm.setAge(20);
        candidatePersonalForm.setNational_insurance("QQ567893A");
        candidatePersonalForm.setGender("Male");
        candidatePersonalForm.setEthnicity("White");
        candidatePersonalForm.setSexuality("Heterosexual");

        Set<ConstraintViolation<CandidatePersonalForm>> violations = validator.validate(candidatePersonalForm);

        assertTrue(violations.isEmpty());
    }

}
