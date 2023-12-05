package ru.sberbank.edu;

import java.io.IOException;

public class AppException extends RuntimeException {

    public AppException(String message) {
        super(message);
    }

    public AppException(IOException e) {
        super(e);
    }
}
