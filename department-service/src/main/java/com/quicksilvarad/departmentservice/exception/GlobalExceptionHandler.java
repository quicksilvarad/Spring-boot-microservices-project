package com.quicksilvarad.departmentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class) //Exception class is super class of all the exceptions hence this will handle all the exceptions thrown unless a class is specified for a specific exception
    public ResponseEntity<ErrorDetails> handleAllException(Exception exception, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage() , webRequest.getDescription(false), "LOOK_FOR_ERRORS_IN_REQUEST" );
        System.out.println(exception.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.I_AM_A_TEAPOT);
    }

    @ExceptionHandler(ResourceNotFoundException.class) //specific class for handling exception
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage() , webRequest.getDescription(false), "USER_NOT_FOUND" );
        System.out.println(exception.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

}
