package com.wolverineteam.ngpuppies.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsernameInvalidInputException extends Exception {

    public UsernameInvalidInputException(String ex){
        super(ex);
    }
}
