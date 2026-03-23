package com.myrecipewhisper.backend.common.exceptions.cuisine;

public class CuisineNotFoundException extends RuntimeException {
    public CuisineNotFoundException(String message) {
        super(message);
    }

}
