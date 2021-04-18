package io.payment.api.handler;

import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;

import org.springdoc.api.ErrorMessage;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DatabaseExceptionHandler {
	
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> conflict(DataIntegrityViolationException e) {

        String message = NestedExceptionUtils.getMostSpecificCause(e).getMessage();
        ErrorMessage errorMessage = new ErrorMessage(message); 
        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }
    
    @ExceptionHandler({ NoSuchElementException.class, EntityNotFoundException.class})
    public ResponseEntity<?> notFound(NoSuchElementException e) {

        String message = NestedExceptionUtils.getMostSpecificCause(e).getMessage();
        ErrorMessage errorMessage = new ErrorMessage(message); 
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

}
