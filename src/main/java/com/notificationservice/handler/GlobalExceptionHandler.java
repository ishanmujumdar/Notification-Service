package com.notificationservice.handler;

import com.notificationservice.entity.ResponseEntities.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handelException(Exception exc)
    {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setStatus_code(HttpStatus.NOT_FOUND.value());
        exceptionResponse.setMessage(exc.getMessage());
        exceptionResponse.setTime(LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
