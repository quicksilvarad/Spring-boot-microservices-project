package com.quicksilvarad.departmentservice.service.impl;

import com.quicksilvarad.departmentservice.DTO.DepartmentDTO;
import com.quicksilvarad.departmentservice.entity.Department;
import com.quicksilvarad.departmentservice.exception.ResourceNotFoundException;
import com.quicksilvarad.departmentservice.repository.DepartmentRepository;
import com.quicksilvarad.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired //necessary for dependency based injection
    private DepartmentRepository departmentRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) {
        //convert DepartmentDTO into Department JPA entity
        //Department department = new Department(departmentDTO.getId(), departmentDTO.getDepartmentName(), departmentDTO.getDepartmentDescription(), departmentDTO.getDepartmentCode());
        Department department = modelMapper.map(departmentDTO,Department.class);
        Department savedDept = departmentRepository.save(department);
        return modelMapper.map(savedDept,DepartmentDTO.class);
    }



    @Override
    public DepartmentDTO getDepartmentByCode(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode).orElseThrow(() -> new ResourceNotFoundException("Department","DepartmentCode",departmentCode));
        return modelMapper.map(department,DepartmentDTO.class);
    }
}
