package com.wolverineteam.ngpuppies.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EikAlreadyExistException extends Exception{

    public EikAlreadyExistException(String ex){
        super(ex);
    }
}
