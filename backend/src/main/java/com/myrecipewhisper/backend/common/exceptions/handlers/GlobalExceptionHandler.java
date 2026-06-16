package com.myrecipewhisper.backend.common.exceptions.handlers;

import com.myrecipewhisper.backend.api.ApiResponse;
import com.myrecipewhisper.backend.common.exceptions.ExternalApiException;
import com.myrecipewhisper.backend.common.exceptions.user.EmailAlreadyUsedException;
import com.myrecipewhisper.backend.common.exceptions.user.UserNotFoundException;
import com.myrecipewhisper.backend.common.exceptions.user.UsernameAlreadyUsedException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
        /**
         * Handle Resource Not Found Exception.
         * <p>
         * Triggered when a requested resource does not exist in the system.
         * Returns a 404 Not Found status with error details.
         */
        @ExceptionHandler({
                        UserNotFoundException.class
        })
        public ResponseEntity<ApiResponse<Void>> handleNotFound(RuntimeException ex) {
                log.warn("Resource not found: {}", ex.getMessage());
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(new ApiResponse<>(null, ex.getMessage()));
        }

        /**
         * Handle Conflict Exceptions.
         * <p>
         * Triggered when a resource already exists (duplicate email, username,
         * ingredient, cuisine, etc.).
         * Returns a 409 Conflict status with error details.
         */
        @ExceptionHandler({
                        EmailAlreadyUsedException.class,
                        UsernameAlreadyUsedException.class
        })
        public ResponseEntity<ApiResponse<Void>> handleConflict(RuntimeException ex) {
                log.warn("Conflict: {}", ex.getMessage());
                return ResponseEntity.status(HttpStatus.CONFLICT)
                                .body(new ApiResponse<>(null, ex.getMessage()));
        }

        /**
         * Handle Illegal Argument Exception.
         * <p>
         * Triggered when an invalid argument is passed to a method.
         * Returns a 400 Bad Request status with error details.
         */
        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<ApiResponse<Void>> handleBadRequest(IllegalArgumentException ex) {
                log.warn("Bad request: {}", ex.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(new ApiResponse<>(null, ex.getMessage()));
        }

        /**
         * Handle Validation Errors (MethodArgumentNotValidException).
         * <p>
         * Triggered when @Valid fails on DTO fields.
         * Returns a 400 Bad Request status with detailed validation errors.
         */
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ApiResponse<Map<String, String>>> handleValidation(MethodArgumentNotValidException ex) {

                Map<String, String> errors = new HashMap<>();
                ex.getBindingResult().getFieldErrors()
                                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

                log.warn("Validation failed: {}", errors);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(new ApiResponse<>(errors, "Validation failed"));
        }

        // -------------------------------------------------------------------------
        // 500 - INTERNAL SERVER ERROR
        // -------------------------------------------------------------------------

        /**
         * Handle Unexpected Exceptions.
         * <p>
         * Catches any unhandled exception to avoid exposing internal system details.
         * Returns a 500 Internal Server Error with a generic message.
         */
        @ExceptionHandler(Exception.class)
        public ResponseEntity<ApiResponse<Void>> handleUnexpected(Exception ex) {
                log.error("Unexpected error occurred", ex);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(new ApiResponse<>(null, ex.getMessage()));
        }
        /*
         * Handle Invalid Date Format in JSON Requests.
         *
         * Triggered when a JSON request contains an invalid date format (e.g., for the
         * birthday field).
         * Returns a 400 Bad Request status with a specific message about the expected
         * date format.
         */

        @ExceptionHandler(HttpMessageNotReadableException.class)
        public ResponseEntity<String> handleInvalidDate(HttpMessageNotReadableException ex) {

                if (ex.getMessage().contains("LocalDate")) {
                        return ResponseEntity
                                        .badRequest()
                                        .body("Birthday must follow format yyyy-MM-dd");
                }

                return ResponseEntity
                                .badRequest()
                                .body("Invalid request");
        }

        /**
         * Handle External API Errors.
         */

        @ExceptionHandler(ExternalApiException.class)
        public ResponseEntity<ApiResponse<?>> handleExternalApi(ExternalApiException ex) {
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                                .body(new ApiResponse<>(null, ex.getMessage()));
        }

}
