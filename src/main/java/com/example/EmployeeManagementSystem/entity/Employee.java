package com.example.EmployeeManagementSystem.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @Min(value = 18)
    private int age;

    private String phoneNo;


}
