package com.quicksilvarad.employeeservice.controller;

import com.quicksilvarad.employeeservice.DTO.APIResponseDTO;
import com.quicksilvarad.employeeservice.DTO.EmployeeDTO;
import com.quicksilvarad.employeeservice.entity.Employee;
import com.quicksilvarad.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/employees")
public class EmployeeController {

    private EmployeeService employeeService;
    @PostMapping
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody EmployeeDTO employeeDTO)
    {
        EmployeeDTO savedEmployeeDTO = employeeService.saveEmployee(employeeDTO);
        return new ResponseEntity<>(savedEmployeeDTO, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<APIResponseDTO> getEmployeeById(@PathVariable("id") Long id)
    {
        APIResponseDTO fetchedEmployee = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(fetchedEmployee,HttpStatus.OK);
    }
}
