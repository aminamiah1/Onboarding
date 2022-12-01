package com.example.ase2022y203.candidateReferences.web.forms;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReferenceForm {
    private Integer id;
    private Integer c_id;
    private String referee_name;
    private String referee_phone_number;

    public ReferenceForm(Integer cid) {
    this(0, cid, "", "");
    }
}
