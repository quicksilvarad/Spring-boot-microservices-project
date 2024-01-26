package com.quicsilvarad.organizationservice.service.impl;

import com.quicsilvarad.organizationservice.DTO.OrganizationDTO;
import com.quicsilvarad.organizationservice.entity.Organization;
import com.quicsilvarad.organizationservice.repository.OrganizationRepository;
import com.quicsilvarad.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OrganizationServiceImpl implements OrganizationService {

    public ModelMapper modelMapper;
    public OrganizationRepository organizationRepository;
    @Override
    public OrganizationDTO saveOrganization(OrganizationDTO organizationDTO) {
        Organization organization = modelMapper.map(organizationDTO,Organization.class);
        Organization savedOrganization = organizationRepository.save(organization);
        return modelMapper.map(savedOrganization,OrganizationDTO.class);

    }

    @Override
    public OrganizationDTO getOrganizationByCode(String organizationCode) {
        Organization organization = organizationRepository.getOrganizationByOrganizationCode(organizationCode);
        return modelMapper.map(organization,OrganizationDTO.class);
    }
}
