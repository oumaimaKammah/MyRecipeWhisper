package com.myrecipewhisper.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle Resource Not Found Exception
     * Returns a 404 nO ot found status with error details
     */

    @ExceptionHandler(RessourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handelNotFound(RessourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("Resource Not Found", ex.getMessage()));
    }

    /**
     * Handle Email Already Used Exception
     * Returns a 409 Conflict status with error details
     */

    @ExceptionHandler(EmailAlreadyUsedException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyUsed(EmailAlreadyUsedException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse("Email Already Used", ex.getMessage()));
    }

    /**
     * Handle Username Already Used Exception
     * Returns a 409 Conflict status with error details
     */

    @ExceptionHandler(UsernameAlreadyUsedException.class)
    public ResponseEntity<ErrorResponse> handleUsernameAlreadyUsed(UsernameAlreadyUsedException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse("Username Already Used", ex.getMessage()));
    }

    /**
     * Handle Illegal Argument Exception
     * Returns a 400 Bad Request status with error details
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Bad Request", ex.getMessage()));
    }

    /**
     * * Handles any unexpected exception not covered by other handlers. * Prevents
     * the API from exposing internal errors. * Returns HTTP 500 Internal Server
     * Error.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("Internal Server Error", "An unexpected error occurred."));
    }

}
