package com.quicksilvarad.departmentservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Entity @Table(name="departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //to mark id as a primary key and generate the ids sequesntially
    private Long id;
    private String departmentName;
    private String departmentDescription;
    private String departmentCode;
}
