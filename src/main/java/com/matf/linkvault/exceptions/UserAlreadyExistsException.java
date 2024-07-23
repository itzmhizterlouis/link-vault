package com.matf.linkvault.exceptions;

public class UserAlreadyExistsException extends ClientSideException{

    public UserAlreadyExistsException(String email) {

        super ("User with email " + email + " already exists.");
    }
}
