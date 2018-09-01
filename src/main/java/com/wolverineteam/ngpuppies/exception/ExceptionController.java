package com.wolverineteam.ngpuppies.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    private Log logger = LogFactory.getLog(ExceptionController.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ExceptionResponse>generalException (Exception ex)throws Exception{

        ExceptionResponse eR = new ExceptionResponse();
        eR.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        eR.setDescription(ex.getMessage());
        logger.error(eR);
        return new ResponseEntity<ExceptionResponse>(eR,HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
