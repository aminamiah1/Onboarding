package com.example.ase2022y203.candidate.web.forms;

import com.example.ase2022y203.candidate.web.UniqueId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateForm<message> {
    @UniqueId
    @NotNull(message ="{id.invalid}")
    private Integer id;
    @NotEmpty(message = "{First_name.invalid}")
    private String First_name;
    @NotEmpty(message = "{Surname.invalid}")
    private String surname;
    @NotNull(message = "{email.invalid}")
    private String email;
    @NotNull(message = "{password.invalid}")
    private String password;
}

