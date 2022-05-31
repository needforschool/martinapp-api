package com.martinapp.api.exception;

import java.util.UUID;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(UUID id) {
        this(id.toString());
    }

    public CustomerNotFoundException(String id) {
        super("User not found : " + id);
    }
}
