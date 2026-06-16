package com.myrecipewhisper.backend.common.exceptions.user;

public class UsernameAlreadyUsedException extends RuntimeException {
    public UsernameAlreadyUsedException(String message) {
        super(message);
    }

}
