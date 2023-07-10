package com.nitisha.sdms.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nitisha.sdms.helper.ApiResponse;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHaandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundExceptionHandler(ResourceNotFoundException Exp) {
        String message = Exp.getLocalizedMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(message, false));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> constraintViolationException(ConstraintViolationException Exp) {
        Map<String, String> response = new HashMap<>();
        Set<ConstraintViolation<?>> constraintViolations = Exp.getConstraintViolations();
        System.err.println("all error"+constraintViolations);
        constraintViolations.forEach((error)->{
            String fieldName = error.getPropertyPath().toString();
            String message = error.getMessage();
            response.put(fieldName, message);
            System.out.println("\n"+"Errors : "+error +"\n");
        });
        System.err.println("\n"+"map element : "+response);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
