package com.matf.linkvault.exceptions;

public class UserAlreadyExistsException extends Exception{

    public UserAlreadyExistsException(String email) {

        super ("User with email " + email + " already exists.");
    }
}
