package com.myrecipewhisper.backend.common.exceptions.cuisine;

public class CuisineAlreadyExistsException extends RuntimeException {
    public CuisineAlreadyExistsException(String message) {
        super(message);
    }

}
