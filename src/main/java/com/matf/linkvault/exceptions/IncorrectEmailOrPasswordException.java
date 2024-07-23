package com.matf.linkvault.exceptions;

public class IncorrectEmailOrPasswordException extends ClientSideException{

    public IncorrectEmailOrPasswordException() {

        super("Incorrect Username or Password");
    }
}
