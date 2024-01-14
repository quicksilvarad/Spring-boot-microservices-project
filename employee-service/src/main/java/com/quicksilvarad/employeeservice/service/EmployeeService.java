package com.quicksilvarad.employeeservice.service;

import com.quicksilvarad.employeeservice.DTO.EmployeeDTO;

public interface EmployeeService {

    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO getEmployeeById(Long id);
}
