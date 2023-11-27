package com.example.EmployeeManagementSystem.exception;


import com.example.EmployeeManagementSystem.dto.response.EmployeeResponseDTO;
import com.example.EmployeeManagementSystem.entity.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler({EmployeeNotFoundException.class, BadRequestException.class})
    public ResponseEntity<EmployeeResponseDTO<Object>>handleCustomExceptions(RuntimeException e, WebRequest webRequest){
        EmployeeResponseDTO<Object> employeeResponseDTO=new EmployeeResponseDTO<>();
        employeeResponseDTO.setSuccess(false);
        employeeResponseDTO.setMessage(e.getMessage());
        if(e instanceof EmployeeNotFoundException)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(employeeResponseDTO);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(employeeResponseDTO);
    }
}
