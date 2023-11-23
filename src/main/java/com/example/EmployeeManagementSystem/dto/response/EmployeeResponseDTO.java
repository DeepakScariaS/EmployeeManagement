package com.example.EmployeeManagementSystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeResponseDTO<T> {
    private Boolean success;
    private String message;
    private T result;


}
