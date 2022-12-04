package com.example.ase2022y203.candidateReferences.web.forms;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class ReferenceForm {
    private Integer id;
    private Integer c_id;
    @NotEmpty
    @Pattern(regexp = "^([a-zA-Z]{2,}\\s[a-zA-Z]{1,}'?-?[a-zA-Z]{2,}\\s?([a-zA-Z]{1,})?)", message="Not correct format for referee name")
    @Size(min = 1, max=200, message = "Referee name must be between 1 and 200 characters")
    private String referee_name;
    @NotEmpty
    @Size(min=9, max=13, message = "Phone number must be between 9 and 13 characters")
    private String referee_phone_number;

    public ReferenceForm() {
        this(0, 0, "", "");
    }
}
