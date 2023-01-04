package com.example.ase2022y203.candidate.api.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder(builderMethodName = "of")
public class JSON {
    private Integer id;
    private String first_name;
    private String surname;
    private String email;
    private String password;
    private String company_name;
}
