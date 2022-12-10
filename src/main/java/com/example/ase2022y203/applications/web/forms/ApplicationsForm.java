package com.example.ase2022y203.applications.web.forms;

import com.example.ase2022y203.applications.domain.Applications;
import com.example.ase2022y203.candidate.domain.Candidate;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class ApplicationsForm {
    private Integer id;
    @NotEmpty
    private String appstatus;
    private Candidate cid;

    public ApplicationsForm(){
        this(0, " ", null);
    }
}
