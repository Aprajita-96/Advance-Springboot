package com.basic.springboot.GlobalException;

public class CustomException extends RuntimeException{
    private String errorMessage;
    private String errorCode;
    private int statusCode;

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public CustomException(String errorCode, String errorMessage, int statusCode){
        super(errorMessage);
        this.errorCode=errorCode;
        this.errorMessage=errorMessage;
        this.statusCode=statusCode;
    }
    public CustomException(){ super();}
}
