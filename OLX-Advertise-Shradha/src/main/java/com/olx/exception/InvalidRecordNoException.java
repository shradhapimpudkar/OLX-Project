package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)

public class InvalidRecordNoException extends RuntimeException {

    private String message;

    public InvalidRecordNoException() {

    }

    public InvalidRecordNoException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String toString() {
        return "Invalid stock id : " + this.message;
    }
}
