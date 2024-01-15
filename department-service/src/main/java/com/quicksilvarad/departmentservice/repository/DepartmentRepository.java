package com.quicksilvarad.departmentservice.repository;

import com.quicksilvarad.departmentservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findByDepartmentCode(String departmentCode);//JPA will make a method based on the naming convention
}
