package org.rossie.videoPlatform.exception;

import org.rossie.videoPlatform.controller.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({EntityExistsException.class})
    public ResponseEntity<Object> handleEntityExistsException(EntityExistsException exception) {
        return ResponseHandler.error(null,
                exception.getMessage(), HttpStatus.IM_USED);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException exception){
        return ResponseHandler.error(null,
                exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InvalidLoginException.class})
    public ResponseEntity<Object> handleInvalidLoginException(InvalidLoginException exception){
        return ResponseHandler.error(null,
                exception.getMessage(), HttpStatus.NOT_FOUND);
    }@ExceptionHandler({TokenExpiredException.class})
    public ResponseEntity<Object> handleTokenExpiredException(TokenExpiredException exception){
        return ResponseHandler.error(null,
                exception.getMessage(), HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
    }
}