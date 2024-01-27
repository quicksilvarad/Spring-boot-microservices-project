package com.quicksilvarad.employeeservice.service.impl;

import com.quicksilvarad.employeeservice.DTO.APIResponseDTO;
import com.quicksilvarad.employeeservice.DTO.DepartmentDTO;
import com.quicksilvarad.employeeservice.DTO.EmployeeDTO;
import com.quicksilvarad.employeeservice.DTO.OrganizationDTO;
import com.quicksilvarad.employeeservice.entity.Employee;
import com.quicksilvarad.employeeservice.exception.ResourceNotFoundException;
import com.quicksilvarad.employeeservice.repository.EmployeeRepository;
import com.quicksilvarad.employeeservice.service.APIClient;
import com.quicksilvarad.employeeservice.service.APIClient2;
import com.quicksilvarad.employeeservice.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Autowired
    private APIClient2 apiClient2;

    @Autowired
    private static final Logger LOGGER= LoggerFactory.getLogger(EmployeeServiceImpl.class);
    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        //Employee employee = new Employee(employeeDTO.getId(),employeeDTO.getFirstName(),employeeDTO.getLastName(),employeeDTO.getEmail(),employeeDTO.getDesignation());
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        Employee savedEmployee = employeeRepository.save(employee);
        return modelMapper.map(savedEmployee, EmployeeDTO.class);
    }

    @Override
    @Retry(name="${spring.application.name}" ,fallbackMethod = "getDefaultDepartment")
    //@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    public APIResponseDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findEmployeeById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
        LOGGER.info("inside getEmployeeById");
        //ResponseEntity<DepartmentDTO> resposnseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/"+employee.getDepartmentCode(), DepartmentDTO.class);
        //DepartmentDTO departmentDTO = resposnseEntity.getBody();
        //DepartmentDTO departmentDTO=webClient.get().uri("http://localhost:8080/api/departments/"+employee.getDepartmentCode()).retrieve().bodyToMono(DepartmentDTO.class).block();
        //OrganizationDTO organizationDTO=webClient.get().uri("http://localhost:8084/api/organizations/"+employee.getOrganizationCode()).retrieve().bodyToMono(OrganizationDTO.class).block();
        DepartmentDTO departmentDTO = apiClient.getDepartment(employee.getDepartmentCode());
        LOGGER.info("after departmentDTO request");
        OrganizationDTO organizationDTO = apiClient2.getOrganization(employee.getOrganizationCode());
        EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);

        APIResponseDTO apiResponseDTO = new APIResponseDTO(employeeDTO, departmentDTO,organizationDTO);
        return apiResponseDTO;
    }

    public APIResponseDTO getDefaultDepartment(Long id,Exception exception) {
        LOGGER.warn("inside getDefaultDepartment");
        Employee employee = employeeRepository.findEmployeeById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
        EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
        DepartmentDTO departmentDTO =  new DepartmentDTO(null,"Employee","Employee of the organisation","ED");
        OrganizationDTO organizationDTO =  new OrganizationDTO(null,"Laxmi Chit Fund","Are mujhe chakkar aa rahe hain","ITUS",null);
        return new APIResponseDTO(employeeDTO,departmentDTO,organizationDTO);
    }


}

