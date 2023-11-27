package com.example.EmployeeManagementSystem.exception;

public class InvalidAgeException  extends RuntimeException{
    public InvalidAgeException(String str){
        super(str);
    }
}
