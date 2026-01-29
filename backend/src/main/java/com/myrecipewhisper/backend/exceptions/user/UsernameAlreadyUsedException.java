package com.myrecipewhisper.backend.exceptions.user;

public class UsernameAlreadyUsedException extends RuntimeException {
    public UsernameAlreadyUsedException(String message) {
        super(message);
    }

}
