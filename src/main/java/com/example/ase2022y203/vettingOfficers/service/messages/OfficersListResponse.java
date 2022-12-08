package com.example.ase2022y203.vettingOfficers.service.messages;

import com.example.ase2022y203.vettingOfficers.service.VettingOfficersDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor
@Builder(builderMethodName = "of")
public class OfficersListResponse {
    private OfficersListRequest request;
    private List<VettingOfficersDTO> officersDTOS;
}
