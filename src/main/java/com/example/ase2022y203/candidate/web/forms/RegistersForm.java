package com.example.ase2022y203.candidate.web.forms;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
public class RegistersForm {
    private Integer ID;
    private Integer c_id;

    @NotEmpty
    @Pattern(regexp = "^[A-Za-z]*$", message = "Invalid Input")
    private String first_name;
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z]*$", message = "Invalid Input")
    private String surname;
    @NotEmpty
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String company_name;
    public RegistersForm(){this(0, 0, "", "", "","","");
    }
}
