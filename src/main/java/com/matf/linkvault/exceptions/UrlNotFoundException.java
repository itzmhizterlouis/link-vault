package com.matf.linkvault.exceptions;

public class UrlNotFoundException extends EntityNotFoundException{

    public UrlNotFoundException () {

        super("URL");
    }
}
