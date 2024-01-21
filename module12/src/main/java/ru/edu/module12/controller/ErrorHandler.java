package ru.edu.module12.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.edu.module12.exception.ItemNotFoundException;
import ru.edu.module12.exception.ServiceError;

import java.util.Date;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ServiceError> handleError(ItemNotFoundException ex) {
        ServiceError serviceError = new ServiceError();
        serviceError.setStatus(HttpStatus.NOT_FOUND.value());
        serviceError.setTimeStamp(new Date().getTime());
        serviceError.setDetails(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(serviceError);
    }
}
