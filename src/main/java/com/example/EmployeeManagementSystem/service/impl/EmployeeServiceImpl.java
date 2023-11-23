package com.example.EmployeeManagementSystem.service.impl;

import com.example.EmployeeManagementSystem.dto.request.EmployeeRequestDTO;
import com.example.EmployeeManagementSystem.dto.response.EmployeeResponseDTO;
import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.repository.EmployeeRepository;
import com.example.EmployeeManagementSystem.service.EmployeeService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponseDTO<Employee> addEmployee(EmployeeRequestDTO employeeRequestDTO) {
        Employee employee=new Employee();
        employee.setName(employeeRequestDTO.getName());
        employee.setAge(employeeRequestDTO.getAge());
        employee.setPhoneNo(employeeRequestDTO.getPhoneNo());
        employeeRepository.save(employee);
        //hsjsjsj
        return new EmployeeResponseDTO<Employee>(true,"Added Successfully",employee);
    }

    @Override
    public EmployeeResponseDTO<List<Employee>> findAll() {
        List<Employee> employees=employeeRepository.findAll();
        return new EmployeeResponseDTO<>(true,"Employee List",employees);
    }

    @Override
    public EmployeeResponseDTO<Employee> update(long id, EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = employeeRepository.findById(id).orElse(null);

            employee.setName(employeeRequestDTO.getName());
            employee.setAge(employeeRequestDTO.getAge());
            employee.setPhoneNo(employeeRequestDTO.getPhoneNo());
            employeeRepository.save(employee);
        return new EmployeeResponseDTO<Employee>(true, "Updated Successfully", employee);
    }

    @Override
    public EmployeeResponseDTO<Employee> delete(long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        employeeRepository.delete(employee);
        return new EmployeeResponseDTO<Employee>(true, "Deleted Successfully", employee);
    }
}