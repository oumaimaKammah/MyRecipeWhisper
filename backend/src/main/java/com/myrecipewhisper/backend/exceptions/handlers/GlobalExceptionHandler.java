package com.myrecipewhisper.backend.exceptions.handlers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.myrecipewhisper.backend.exceptions.EmailAlreadyUsedException;
import com.myrecipewhisper.backend.exceptions.RessourceNotFoundException;
import com.myrecipewhisper.backend.exceptions.UsernameAlreadyUsedException;
import com.myrecipewhisper.backend.exceptions.ingredient.IngredientAlreadyExistsException;
import com.myrecipewhisper.backend.exceptions.ingredient.IngredientNotFoundException;

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

    /**
     * Handle Method Argument Not Valid Exception
     * Returns a 400 Bad Request status with validation error details
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("Validation Failed", errors.toString()));
    }

    /**
     * Handle Ingredient Already Exists Exception
     * Returns a 409 Conflict status with error details
     */
    @ExceptionHandler(IngredientAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleIngredientAlreadyExists(IngredientAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse("Ingredient Already Exists", ex.getMessage()));
    }

    /**
     * Handle Ingredient Not Found Exception
     * Returns a 404 Not Found status with error details
     */
    @ExceptionHandler(IngredientNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleIngredientNotFound(IngredientNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("Ingredient Not Found", ex.getMessage()));
    }

}
