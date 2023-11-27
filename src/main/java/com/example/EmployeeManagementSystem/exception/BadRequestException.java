package com.example.EmployeeManagementSystem.exception;

public class BadRequestException extends RuntimeException {
    private final String message;

    public BadRequestException(String message){this.message=message;}

    @Override
    public String toString(){return this.message;}

    public String getMessage(){return message;}
}
