package com.wolverineteam.ngpuppies.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenSubscriberException extends Exception{

    public ForbiddenSubscriberException(String ex){
        super(ex);
    }
}
