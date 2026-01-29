package com.myrecipewhisper.backend.exceptions.cuisine;

public class CuisineNotFoundException extends RuntimeException {
    public CuisineNotFoundException(String message) {
        super(message);
    }

}
