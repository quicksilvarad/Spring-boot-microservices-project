package com.quicsilvarad.organizationservice.service;

import com.quicsilvarad.organizationservice.DTO.OrganizationDTO;
import com.quicsilvarad.organizationservice.entity.Organization;

public interface OrganizationService {

    OrganizationDTO saveOrganization(OrganizationDTO organizationDTO);
    OrganizationDTO getOrganizationByCode(String organizationCode);
}
