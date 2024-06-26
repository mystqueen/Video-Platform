package org.rossie.videoPlatform.exception;

public class InvalidLoginException extends RuntimeException {
    public InvalidLoginException(String message){
        super(message);
    }
}
