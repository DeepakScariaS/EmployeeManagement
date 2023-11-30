package com.example.EmployeeManagementSystem.service.impl;

import com.example.EmployeeManagementSystem.dto.request.EmployeeRequestDTO;
import com.example.EmployeeManagementSystem.dto.response.EmployeeResponseDTO;
import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.exception.BadRequestException;
import com.example.EmployeeManagementSystem.exception.EmployeeNotFoundException;
import com.example.EmployeeManagementSystem.exception.InvalidAgeException;
import com.example.EmployeeManagementSystem.repository.EmployeeRepository;
import com.example.EmployeeManagementSystem.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;



    @Override
    public EmployeeResponseDTO<Employee> addEmployee(EmployeeRequestDTO employeeRequestDTO) throws InvalidAgeException {
        int age=employeeRequestDTO.getAge();
        if(age<18){
            throw new BadRequestException("Employee age must be greater than 18");
        }
        Employee employee=new Employee();
        employee.setName(employeeRequestDTO.getName());
        employee.setAge(age);
        employee.setPhoneNo(employeeRequestDTO.getPhoneNo());
        employeeRepository.save(employee);
        return new EmployeeResponseDTO<Employee>(true,"Added Successfully",employee);
    }

    @Override
    public EmployeeResponseDTO<List<Employee>> findAll() {
        List<Employee> employees=employeeRepository.findAll();
        return new EmployeeResponseDTO<>(true,"Employee List",employees);
    }


    @Override
    public EmployeeResponseDTO<Employee> update(long id, EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new
                EmployeeNotFoundException("Employee not found with ID : "+id));

            employee.setName(employeeRequestDTO.getName());
            employee.setAge(employeeRequestDTO.getAge());
            employee.setPhoneNo(employeeRequestDTO.getPhoneNo());
            employeeRepository.save(employee);
        return new EmployeeResponseDTO<Employee>(true, "Updated Successfully", employee);
    }

    @Override
    public EmployeeResponseDTO<Employee> delete(long id) {

                employeeRepository.deleteById(id);
        return new EmployeeResponseDTO<Employee>(true, "Deleted Successfully", null);
    }
}
