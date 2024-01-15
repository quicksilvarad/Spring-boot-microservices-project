package com.quicksilvarad.employeeservice.service.impl;

import com.quicksilvarad.employeeservice.DTO.EmployeeDTO;
import com.quicksilvarad.employeeservice.entity.Employee;
import com.quicksilvarad.employeeservice.exception.ResourceNotFoundException;
import com.quicksilvarad.employeeservice.repository.EmployeeRepository;
import com.quicksilvarad.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        //Employee employee = new Employee(employeeDTO.getId(),employeeDTO.getFirstName(),employeeDTO.getLastName(),employeeDTO.getEmail(),employeeDTO.getDesignation());
        Employee employee = modelMapper.map(employeeDTO,Employee.class);
        Employee savedEmployee = employeeRepository.save(employee);
        return modelMapper.map(savedEmployee,EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findEmployeeById(id).orElseThrow(()-> new ResourceNotFoundException("Employee","Id",id));
        return modelMapper.map(employee,EmployeeDTO.class); }
}
