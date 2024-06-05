package org.rossie.videoPlatform.exception;

import org.rossie.videoPlatform.controller.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {

    @ExceptionHandler({EntityExistsException.class})
    public ResponseEntity<Object> handleEntityExixtsException(EntityExistsException exception) {
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
    }
}
