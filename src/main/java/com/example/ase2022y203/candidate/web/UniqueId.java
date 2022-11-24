package com.example.ase2022y203.candidate.web;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = UniqueIdValidator.class)
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
public @interface UniqueId {
    String message() default "The candidate id must be unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

