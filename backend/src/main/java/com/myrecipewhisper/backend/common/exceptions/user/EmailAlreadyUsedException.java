package com.myrecipewhisper.backend.common.exceptions.user;

public class EmailAlreadyUsedException extends RuntimeException {
    public EmailAlreadyUsedException(String message) {
        super(message);
    }

}
