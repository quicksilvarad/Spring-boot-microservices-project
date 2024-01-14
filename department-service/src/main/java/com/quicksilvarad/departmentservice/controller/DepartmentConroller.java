package com.quicksilvarad.departmentservice.controller;

import com.quicksilvarad.departmentservice.DTO.DepartmentDTO;
import com.quicksilvarad.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/departments")
@AllArgsConstructor
public class DepartmentConroller {
    private DepartmentService departmentService;
//Build save dept rest api
    @PostMapping

    public ResponseEntity<DepartmentDTO> saveDepartment(@RequestBody DepartmentDTO departmentDTO)
    {
        DepartmentDTO savedDepartmentDTO = departmentService.saveDepartment(departmentDTO);
        return new ResponseEntity<>(savedDepartmentDTO, HttpStatus.CREATED);
    }

    @GetMapping ("{code}")
public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable("code") String departmentCode)
    {
        return new ResponseEntity<>(departmentService.getDepartmentByCode(departmentCode),HttpStatus.OK);
    }
}
