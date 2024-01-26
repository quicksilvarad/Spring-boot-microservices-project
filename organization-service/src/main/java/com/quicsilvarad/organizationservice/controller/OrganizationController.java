package com.quicsilvarad.organizationservice.controller;

import com.quicsilvarad.organizationservice.DTO.OrganizationDTO;
import com.quicsilvarad.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/organizations/")
@AllArgsConstructor
public class OrganizationController {

    public OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<OrganizationDTO> saveOrganization(@RequestBody OrganizationDTO organizationDTO)
    {
        OrganizationDTO savedOrganizationDTO = organizationService.saveOrganization(organizationDTO);
        return new ResponseEntity<>(savedOrganizationDTO, HttpStatus.CREATED);
    }

    @GetMapping("{code}")
    public ResponseEntity<OrganizationDTO> getOrganization(@PathVariable("code") String organizationCode)
    {
        OrganizationDTO fetchedOrganizationDTO = organizationService.getOrganizationByCode(organizationCode);
        return  new ResponseEntity<>(fetchedOrganizationDTO,HttpStatus.OK);
    }
}
