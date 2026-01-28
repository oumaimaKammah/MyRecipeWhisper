package com.myrecipewhisper.backend.exceptions.handlers;

import java.util.List;

public record ErrorResponse(
        String message,
        List<String> details) {
    public ErrorResponse(String message, String detail) {
        this(message, List.of(detail));
    }

}
