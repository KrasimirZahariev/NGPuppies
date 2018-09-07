package com.wolverineteam.ngpuppies.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequestCantBeProcessedException extends Exception{

    public RequestCantBeProcessedException(String ex){
        super(ex);
    }
}
