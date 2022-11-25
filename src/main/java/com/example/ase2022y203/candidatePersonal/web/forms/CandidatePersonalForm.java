package com.example.ase2022y203.candidatePersonal.web.forms;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CandidatePersonalForm {
    private Integer id;
    private Integer c_id;
    private String national_insurance;
    private String ethnicity;
    private String gender;
    private String age;
    private String sexuality;
    public CandidatePersonalForm(){this(0, 0, " ", " ", " ", " ", " ");}
}
