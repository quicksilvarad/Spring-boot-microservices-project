package com.quicksilvarad.departmentservice.service;

import com.quicksilvarad.departmentservice.DTO.DepartmentDTO;

public interface DepartmentService {

    DepartmentDTO saveDepartment(DepartmentDTO departmentDTO);


    DepartmentDTO getDepartmentByCode(String departmentCode);
}
