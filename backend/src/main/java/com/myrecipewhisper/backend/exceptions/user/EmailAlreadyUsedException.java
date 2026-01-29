package com.myrecipewhisper.backend.exceptions.user;

public class EmailAlreadyUsedException extends RuntimeException {
    public EmailAlreadyUsedException(String message) {
        super(message);
    }

}
