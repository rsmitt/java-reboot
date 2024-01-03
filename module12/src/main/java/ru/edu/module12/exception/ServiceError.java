package ru.edu.module12.exception;

import lombok.Data;

@Data
public class ServiceError {

    private int status;
    private String details;
    private long timeStamp;
}
