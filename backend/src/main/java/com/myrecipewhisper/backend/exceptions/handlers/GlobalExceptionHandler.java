package com.myrecipewhisper.backend.exceptions.handlers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.myrecipewhisper.backend.api.ApiResponse;
import com.myrecipewhisper.backend.exceptions.EmailAlreadyUsedException;
import com.myrecipewhisper.backend.exceptions.RessourceNotFoundException;
import com.myrecipewhisper.backend.exceptions.UsernameAlreadyUsedException;
import com.myrecipewhisper.backend.exceptions.cuisine.CuisineAlreadyExistsException;
import com.myrecipewhisper.backend.exceptions.cuisine.CuisineNotFoundException;
import com.myrecipewhisper.backend.exceptions.ingredient.IngredientAlreadyExistsException;
import com.myrecipewhisper.backend.exceptions.ingredient.IngredientNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle Resource Not Found Exception
     * Returns a 404 nO ot found status with error details
     */

    @ExceptionHandler(RessourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handelNotFound(RessourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(null, ex.getMessage()));
    }

    /**
     * Handle Email Already Used Exception
     * Returns a 409 Conflict status with error details
     */

    @ExceptionHandler(EmailAlreadyUsedException.class)
    public ResponseEntity<ApiResponse<Void>> handleEmailAlreadyUsed(EmailAlreadyUsedException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiResponse<>(null, ex.getMessage()));
    }

    /**
     * Handle Username Already Used Exception
     * Returns a 409 Conflict status with error details
     */

    @ExceptionHandler(UsernameAlreadyUsedException.class)
    public ResponseEntity<ApiResponse<Void>> handleUsernameAlreadyUsed(UsernameAlreadyUsedException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiResponse<>(null, ex.getMessage()));
    }

    /**
     * Handle Illegal Argument Exception
     * Returns a 400 Bad Request status with error details
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(null, ex.getMessage()));
    }

    /**
     * * Handles any unexpected exception not covered by other handlers. * Prevents
     * the API from exposing internal errors. * Returns HTTP 500 Internal Server
     * Error.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(null, ex.getMessage()));
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
    public ResponseEntity<ApiResponse<Void>> handleIngredientAlreadyExists(IngredientAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiResponse<>(null, ex.getMessage()));
    }

    /**
     * Handle Ingredient Not Found Exception
     * Returns a 404 Not Found status with error details
     */
    @ExceptionHandler(IngredientNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleIngredientNotFound(IngredientNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(null, ex.getMessage()));
    }

    /**
     * Handle Cuisine Already Exists Exception
     * Returns a 409 Conflict status with error details
     */
    @ExceptionHandler(CuisineAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleCuisineAlreadyExists(CuisineAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiResponse<>(null, ex.getMessage()));
    }

    /**
     * Handle Cuisine Not Found Exception
     * Returns a 404 Not Found status with error details
     */
    @ExceptionHandler(CuisineNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleCuisineNotFound(CuisineNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(null, ex.getMessage()));
    }

}
