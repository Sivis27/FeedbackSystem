package com.fms.authenticate.exception;

public class UnauthorizedUserException extends ObjectNotFoundException {
    public UnauthorizedUserException(String userId) {
        super(String.format("The user cannot be accessible. (userId: %s)", userId) + " is Unauthorised ");
    }
}
