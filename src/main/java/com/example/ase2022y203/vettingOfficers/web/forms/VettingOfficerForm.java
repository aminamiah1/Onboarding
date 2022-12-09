package com.example.ase2022y203.vettingOfficers.web.forms;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class VettingOfficerForm {
    private Integer ID;

    @NotEmpty
    @Pattern(regexp = "^[A-Za-z]*$", message = "Invalid Input")
    @Size(min=1, max=100,  message = "First name must be between 1 and 100 characters")
    private String first_name;
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z]*$", message = "Invalid Input")
    @Size(min=1, max=100,  message = "Surname must be between 1 and 100 characters")
    private String surname;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Size(min=2, max=100, message = "Password must be between 2 and 100 characters")
    private String password;

    public VettingOfficerForm(){
        this(0, "", "", "", "");
    }
}
