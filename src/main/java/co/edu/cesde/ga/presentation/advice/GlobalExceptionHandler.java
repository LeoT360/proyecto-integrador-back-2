package co.edu.cesde.ga.presentation.advice;

import co.edu.cesde.ga.domain.exceptions.ResourceAlreadyExistsException;
import co.edu.cesde.ga.domain.exceptions.ResourceConflictException;
import co.edu.cesde.ga.domain.exceptions.ResourceNotFoundException;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<String> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex) {
        String message = ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        String message = ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<String> handleResourceConflictException(ResourceConflictException ex) {
        String message = ex.getCause() != null ? ex.getCause().getMessage() : "Ocurrio un conflicto";

        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }
}