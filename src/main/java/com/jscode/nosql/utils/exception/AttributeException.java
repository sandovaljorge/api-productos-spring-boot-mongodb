package com.jscode.nosql.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AttributeException extends Exception {

    public AttributeException(String message) {
        super(message);
    }
}
