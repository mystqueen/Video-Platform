package org.rossie.videoPlatform.exception;

public class EntityExistsException extends RuntimeException {
    public EntityExistsException(String message){
        super(message);
    }
}
