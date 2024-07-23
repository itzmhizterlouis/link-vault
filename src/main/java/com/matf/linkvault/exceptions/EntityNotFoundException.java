package com.matf.linkvault.exceptions;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String message) {
        super(message + " NOT FOUND");
    }
}
