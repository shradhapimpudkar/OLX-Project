package com.olx.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidCategoryDataException extends RuntimeException {

    private String  message ="";

    public InvalidCategoryDataException() {

    }

    public InvalidCategoryDataException(String message) {
        super();
        this.message=message;
        
    }

    @Override
    public String toString() {
        return "Invalid Masterdata Category Exception  : "+message;
    }
}