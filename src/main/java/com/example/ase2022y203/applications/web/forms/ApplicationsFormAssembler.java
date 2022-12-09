package com.example.ase2022y203.applications.web.forms;

import com.example.ase2022y203.applications.service.ApplicationsDTO;

public class ApplicationsFormAssembler {
    public static ApplicationsForm toApplicationsForm(ApplicationsDTO applicationsDTO){
        return new ApplicationsForm(applicationsDTO.getId(),
                applicationsDTO.getAppstatus(), applicationsDTO.getCid());
    }
}
