package com.example.demo.exception;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@Getter
@Setter
@NoArgsConstructor
@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "not accesible")
public class CustomeException extends RuntimeException {

    private String resourceName;
    private String fieldName;
    private Long fieldValue;

    public CustomeException(String resourceName, String fieldName, Long fieldValue) {
        super();
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public CustomeException(String resourceName, String fieldName) {
        this.resourceName = resourceName;
        this.fieldName = fieldName;
    }

    public CustomeException(String message) {
        super(message);
    }

}
