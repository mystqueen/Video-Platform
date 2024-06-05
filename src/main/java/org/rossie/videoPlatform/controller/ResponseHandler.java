package org.rossie.videoPlatform.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> success(Object data, String message, HttpStatus statusCode){
        Map<String, Object> response = new HashMap<>();
        response.put("data", data);
        response.put("message", message);
        response.put("success", true);

        return new ResponseEntity<>(response, statusCode);
    }

    public static ResponseEntity<Object> error(Object data, String message, HttpStatus statusCode){
        Map<String, Object> response = new HashMap<>();
        response.put("data", data);
        response.put("message", message);
        response.put("success", false);

        return new ResponseEntity<>(response, statusCode);
    }

}
