package com.myrecipewhisper.backend.exceptions.cuisine;

public class CuisineAlreadyExistsException extends RuntimeException {
    public CuisineAlreadyExistsException(String message) {
        super(message);
    }

}
