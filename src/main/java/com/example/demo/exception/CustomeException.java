package com.example.demo.exception;

import java.util.HashMap;
import java.util.Map;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CustomeException extends Exception {
    
    private Map<String, String> error = new HashMap<>();

    public CustomeException(String message,Map<String, String> error2) {
        super(message);
        this.error=error2;
    }

    public CustomeException(Throwable cause) {
        super(cause);
    }

    public CustomeException(String message) {
        super(message);
    }
}
