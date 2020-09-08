package com.basic.springboot.GlobalException;

import lombok.Data;

import java.util.Date;

@Data
public class ExceptionResponse {


    private int statusCode;
    private String message;
    private Date timestamp;
    private String errorCode;

}
