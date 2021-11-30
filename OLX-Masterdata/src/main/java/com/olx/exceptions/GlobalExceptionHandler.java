package com.olx.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {
            InvalidCategoryIdException.class,
            InvalidStatusIdException.class,
            InvalidCategoryDataException.class
    })

    public ResponseEntity<Object> invalidCategoryIdException(RuntimeException exception, WebRequest request) {

        return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    public ResponseEntity<Object> invalidStatusIdException(RuntimeException exception, WebRequest request) {

        return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    
    
    public ResponseEntity<Object> invalidMasterdataCategoryException(RuntimeException exception, WebRequest request) {

        return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}