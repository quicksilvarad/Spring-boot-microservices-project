package com.quicksilvarad.employeeservice.service;


import com.quicksilvarad.employeeservice.DTO.DepartmentDTO;
import com.quicksilvarad.employeeservice.DTO.OrganizationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="ORGANIZATION-SERVICE")
public interface APIClient2 {

    @GetMapping("api/organizations/{code}")
    OrganizationDTO getOrganization(@PathVariable("code") String organizationCode);
}
