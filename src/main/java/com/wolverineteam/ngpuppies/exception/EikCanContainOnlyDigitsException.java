package com.wolverineteam.ngpuppies.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EikCanContainOnlyDigitsException extends Exception {

    public EikCanContainOnlyDigitsException(String ex){
        super(ex);
    }
}
