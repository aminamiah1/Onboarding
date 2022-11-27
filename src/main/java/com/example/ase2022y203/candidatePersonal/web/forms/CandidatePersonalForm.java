package com.example.ase2022y203.candidatePersonal.web.forms;

import com.example.ase2022y203.candidatePersonal.web.forms.validation.NationalInsuranceFormat;
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

    @NationalInsuranceFormat
    private String national_insurance;
    @NotEmpty
    private String ethnicity;
    @NotEmpty
    private String gender;
    @NotNull
    @Range(min = 18, max = 120, message = "Age must be between 18 and 120")
    private Integer age;
    @NotEmpty
    private String sexuality;

    public CandidatePersonalForm(){this(0, 0, " ", " ", " ", 0, " ");}
}
