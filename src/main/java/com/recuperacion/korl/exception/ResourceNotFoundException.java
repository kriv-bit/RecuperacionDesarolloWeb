package com.recuperacion.korl.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message); // Pass the message to the parent RuntimeException [cite: 266]
    }
}