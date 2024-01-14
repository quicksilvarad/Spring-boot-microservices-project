package com.quicksilvarad.departmentservice.repository;

import com.quicksilvarad.departmentservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByDepartmentCode(String departmentCode); //JPA will make a method based on the naming convention
}
