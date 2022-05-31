package com.martinapp.api.exception;

public class InvalidUUIDException extends RuntimeException {
    public InvalidUUIDException(String id) {
        super("Invalid UUID : " + id);
    }
}
