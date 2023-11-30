package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.controller.EmployeeController;
import com.example.EmployeeManagementSystem.dto.request.EmployeeRequestDTO;
import com.example.EmployeeManagementSystem.dto.response.EmployeeResponseDTO;
import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.exception.InvalidAgeException;
import com.example.EmployeeManagementSystem.service.impl.EmployeeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeServiceImpl employeeService;

    @Test
    void addEmployeeTest() throws Exception {
        EmployeeRequestDTO requestDTO = new EmployeeRequestDTO("John", 25, "1234567890");
        EmployeeResponseDTO<Employee> responseDTO = new EmployeeResponseDTO<>(true, "Added Successfully", new Employee());

        when(employeeService.addEmployee(requestDTO)).thenReturn(responseDTO);

        mockMvc.perform(post("/addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Added Successfully"));
    }

    @Test
    void findAllTest() throws Exception {
        EmployeeResponseDTO<List<Employee>> responseDTO = new EmployeeResponseDTO<>(true, "Employee List", Arrays.asList(new Employee(), new Employee()));

        when(employeeService.findAll()).thenReturn(responseDTO);

        mockMvc.perform(get("/employees")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Employee List"));
    }

    @Test
    void updateTest() throws Exception {
        long id = 1L;
        EmployeeRequestDTO requestDTO = new EmployeeRequestDTO("John Doe", 30, "9876543210");
        EmployeeResponseDTO<Employee> responseDTO = new EmployeeResponseDTO<>(true, "Updated Successfully", new Employee());

        when(employeeService.update(id, requestDTO)).thenReturn(responseDTO);

        mockMvc.perform(put("/update/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Updated Successfully"));
    }

    @Test
    void deleteTest() throws Exception {
        long id = 1L;
        EmployeeResponseDTO<Employee> responseDTO = new EmployeeResponseDTO<>(true, "Deleted Successfully", null);

        when(employeeService.delete(id)).thenReturn(responseDTO);

        mockMvc.perform(delete("/delete/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Deleted Successfully"));
    }


    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
