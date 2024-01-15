package com.quicksilvarad.employeeservice.service;

import com.quicksilvarad.employeeservice.DTO.APIResponseDTO;
import com.quicksilvarad.employeeservice.DTO.EmployeeDTO;

public interface EmployeeService {

    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);
    APIResponseDTO getEmployeeById(Long id);
}
