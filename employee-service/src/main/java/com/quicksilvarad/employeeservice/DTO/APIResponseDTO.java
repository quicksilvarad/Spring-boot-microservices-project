package com.quicksilvarad.employeeservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class APIResponseDTO {
    private EmployeeDTO employee;
    private DepartmentDTO department;
}
