package com.example.ase2022y203.candidatePersonal.web.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class CandidatePersonalForm {

    private Integer id;
    private Integer c_id;
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z]{2}[0-9]{6}[a-zA-Z]{1}$", message = "{nationalInsurance.invalid}")
    private String national_insurance;
    private String ethnicity;
    private String gender;
    @NotNull
    @Range(min = 18, max = 120, message = "Age must be between 18 and 120")
    private Integer age;
    private String sexuality;
    public CandidatePersonalForm(){this(0, 0, " ", " ", " ", 0, " ");}
}
