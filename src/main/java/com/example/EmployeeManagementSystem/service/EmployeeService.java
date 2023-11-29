package com.example.EmployeeManagementSystem.service;

import com.example.EmployeeManagementSystem.dto.request.EmployeeRequestDTO;
import com.example.EmployeeManagementSystem.dto.response.EmployeeResponseDTO;
import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.exception.InvalidAgeException;

import java.util.List;

public interface EmployeeService {

    public EmployeeResponseDTO<Employee> addEmployee(EmployeeRequestDTO employeeRequestDTO) throws InvalidAgeException;

    public EmployeeResponseDTO<List<Employee>> findAll();

    public EmployeeResponseDTO<Employee> update(long id,EmployeeRequestDTO employeeRequestDTO);

    public EmployeeResponseDTO<Employee> delete(long id);

}
