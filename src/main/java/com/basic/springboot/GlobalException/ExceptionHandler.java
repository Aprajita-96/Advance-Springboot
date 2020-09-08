package com.basic.springboot.GlobalException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponse> customException(CustomException exception){
        ExceptionResponse error=new ExceptionResponse();
        error.setTimestamp(new Date());
        error.setErrorCode(exception.getErrorCode());
        error.setMessage(exception.getMessage());
        error.setStatusCode(exception.getStatusCode());
        return new ResponseEntity<>(error, HttpStatus.valueOf(exception.getErrorCode()));
    }
}
