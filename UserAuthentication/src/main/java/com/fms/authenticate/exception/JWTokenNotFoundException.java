package com.fms.authenticate.exception;


public class JWTokenNotFoundException extends ObjectNotFoundException {
    public JWTokenNotFoundException(String token) {
        super(String.format("The JWToken cannot be found: %s", token));
    }
}
