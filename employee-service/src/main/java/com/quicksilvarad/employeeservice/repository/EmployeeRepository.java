package com.quicksilvarad.employeeservice.repository;

import com.quicksilvarad.employeeservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Optional<Employee> findEmployeeById(Long id);
    Optional<Employee> findEmployeeByOrganizationCode(String organizationCode);
}
