package com.example.finalProject.advice;

import com.example.finalProject.exceptions.DepartmentDoesNotExistException;
import com.example.finalProject.exceptions.EmployeeDoesNotExistException;
import com.example.finalProject.exceptions.ProjectDoesNotExistException;
import com.example.finalProject.exceptions.WrongArgumentsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(WrongArgumentsException.class)
    public ResponseEntity<String> exceptionWrongArguments(){
        return ResponseEntity
                .badRequest()
                .body("Wrong arguments!");
    }

    @ExceptionHandler(DepartmentDoesNotExistException.class)
    public ResponseEntity<String> exceptionDepartmentDoesNotExist(){
        return ResponseEntity
                .badRequest()
                .body("This Department does not exist!");
    }

    @ExceptionHandler(EmployeeDoesNotExistException.class)
    public ResponseEntity<String> exceptionEmployeeDoesNotExist(){
        return ResponseEntity
                .badRequest()
                .body("This Employee does not exist!");
    }

    @ExceptionHandler(ProjectDoesNotExistException.class)
    public ResponseEntity<String> exceptionProjectDoesNotExist(){
        return ResponseEntity
                .badRequest()
                .body("This Project does not exist!");
    }
}
