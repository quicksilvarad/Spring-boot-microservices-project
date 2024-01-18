package com.quicksilvarad.employeeservice.service.impl;

import com.quicksilvarad.employeeservice.DTO.APIResponseDTO;
import com.quicksilvarad.employeeservice.DTO.DepartmentDTO;
import com.quicksilvarad.employeeservice.DTO.EmployeeDTO;
import com.quicksilvarad.employeeservice.entity.Employee;
import com.quicksilvarad.employeeservice.exception.ResourceNotFoundException;
import com.quicksilvarad.employeeservice.repository.EmployeeRepository;
import com.quicksilvarad.employeeservice.service.APIClient;
import com.quicksilvarad.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

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
    //@Autowired
    //private RestTemplate restTemplate;
    //@Autowired
    //private WebClient webClient;
    @Autowired
    private APIClient apiClient;

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        //Employee employee = new Employee(employeeDTO.getId(),employeeDTO.getFirstName(),employeeDTO.getLastName(),employeeDTO.getEmail(),employeeDTO.getDesignation());
        Employee employee = modelMapper.map(employeeDTO,Employee.class);
        Employee savedEmployee = employeeRepository.save(employee);
        return modelMapper.map(savedEmployee,EmployeeDTO.class);
    }

    @Override
    public APIResponseDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findEmployeeById(id).orElseThrow(()-> new ResourceNotFoundException("Employee","Id",id));
        //ResponseEntity<DepartmentDTO> resposnseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/"+employee.getDepartmentCode(), DepartmentDTO.class);
        //DepartmentDTO departmentDTO = resposnseEntity.getBody();
        //DepartmentDTO departmentDTO=webClient.get().uri("http://localhost:8080/api/departments/"+employee.getDepartmentCode()).retrieve().bodyToMono(DepartmentDTO.class).block();
        DepartmentDTO departmentDTO = apiClient.getDepartment(employee.getDepartmentCode());
        EmployeeDTO employeeDTO = modelMapper.map(employee,EmployeeDTO.class);
        APIResponseDTO apiResponseDTO = new APIResponseDTO(employeeDTO,departmentDTO);
        return apiResponseDTO; }
}
