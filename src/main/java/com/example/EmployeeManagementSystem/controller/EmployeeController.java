package com.example.EmployeeManagementSystem.controller;


import com.example.EmployeeManagementSystem.dto.request.EmployeeRequestDTO;
import com.example.EmployeeManagementSystem.dto.response.EmployeeResponseDTO;
import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.exception.InvalidAgeException;
import com.example.EmployeeManagementSystem.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeServiceImpl;

    @PostMapping("/addEmployee")
    public ResponseEntity<EmployeeResponseDTO<Employee>> addEmployee(@RequestBody EmployeeRequestDTO employeeRequestDTO) throws InvalidAgeException {
        EmployeeResponseDTO<Employee> employeeResponseDTO=employeeServiceImpl.addEmployee(employeeRequestDTO);
        return ResponseEntity.ok(employeeResponseDTO);
    }

    @GetMapping("/employees")
    public ResponseEntity<EmployeeResponseDTO<List<Employee>>> findAll(){
        EmployeeResponseDTO<List<Employee>> all=employeeServiceImpl.findAll();
        return ResponseEntity.ok(all);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeResponseDTO<Employee>> update(@RequestBody EmployeeRequestDTO employeeRequestDTO,
                                                                @PathVariable long id) {
        EmployeeResponseDTO<Employee> employeeResponseDTO = employeeServiceImpl.update(id, employeeRequestDTO);
        return ResponseEntity.ok(employeeResponseDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<EmployeeResponseDTO<Employee>> delete(@PathVariable long id) {
        EmployeeResponseDTO<Employee> employeeResponseDTO = employeeServiceImpl.delete(id);
        return ResponseEntity.ok(employeeResponseDTO);
    }
}
