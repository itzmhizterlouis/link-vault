package com.matf.linkvault.exceptions;

public class InvalidTokenException extends UnauthorizedException {
    public InvalidTokenException(String message) {
        super(message);
    }
}
