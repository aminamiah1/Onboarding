package com.example.ase2022y203.applications.service.messages;

import com.example.ase2022y203.applications.service.ApplicationsDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor
@Builder(builderMethodName = "of")
public class ApplicationsListResponse {
    private ApplicationsListRequest request;
    private List<ApplicationsDTO> applications;
}
