package org.testtask.exceptions;

public class DataIsNotValidException extends RuntimeException {
    public DataIsNotValidException() {
    }

    public DataIsNotValidException(String message) {
        super(message);
    }
}
