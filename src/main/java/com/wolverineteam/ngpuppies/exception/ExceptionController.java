package com.wolverineteam.ngpuppies.exception;

import com.wolverineteam.ngpuppies.models.Currency;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@ControllerAdvice
@EnableWebMvc
public class ExceptionController {
    private Log logger = LogFactory.getLog(ExceptionController.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ExceptionResponse> generalException (Exception ex)throws Exception{

        ExceptionResponse eR = new ExceptionResponse();
        eR.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        eR.setDescription(ex.getMessage());
        logger.error(eR.getDescription());
        System.out.println(ex);
        return new ResponseEntity<>(eR,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value = SubscriberNotFountException.class)
    public  ResponseEntity<ExceptionResponse> subscriberNotFound (SubscriberNotFountException ex){
        ExceptionResponse response = new ExceptionResponse();
        response.setCode(HttpStatus.NOT_FOUND.value());
        response.setDescription(ex.getMessage());
        logger.error(response.getDescription());
        System.out.println(ex);
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ForbiddenSubscriberException.class)
    public ResponseEntity<ExceptionResponse> forbiddenSubscriber (ForbiddenSubscriberException ex){
        ExceptionResponse response = new ExceptionResponse();
        response.setCode(HttpStatus.FORBIDDEN.value());
        response.setDescription(ex.getMessage());
        logger.error(response.getDescription());
        System.out.println(ex);
        return new ResponseEntity<>(response,HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = MissingOrNotCorrectArgumentException.class)
    public ResponseEntity<ExceptionResponse> missingOrNotCorrectArgumentException(MissingOrNotCorrectArgumentException ex){
        ExceptionResponse response = new ExceptionResponse();
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setDescription(ex.getMessage());
        logger.error(response.getDescription());
        System.out.println(ex);
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = FieldCantBeNullException.class)
    public ResponseEntity<ExceptionResponse> fieldCantBeNullException(FieldCantBeNullException ex){
        ExceptionResponse response = new ExceptionResponse();
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setDescription(ex.getMessage());
        logger.error(response.getDescription());
        System.out.println(ex);
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EikCanContainOnlyDigitsException.class)
    public ResponseEntity<ExceptionResponse> eikCanContainOnlyDigitsException(EikCanContainOnlyDigitsException ex){
        ExceptionResponse response = new ExceptionResponse();
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setDescription(ex.getMessage());
        logger.error(response.getDescription());
        System.out.println(ex);
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidRoleException.class)
    public ResponseEntity<ExceptionResponse> invalidRoleException(InvalidRoleException ex){
        ExceptionResponse response = new ExceptionResponse();
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setDescription(ex.getMessage());
        logger.error(response.getDescription());
        System.out.println(ex);
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = PasswordInvalidInputException.class)
    public ResponseEntity<ExceptionResponse> passwordInvalidInputException(PasswordInvalidInputException ex){
        ExceptionResponse response = new ExceptionResponse();
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setDescription(ex.getMessage());
        logger.error(response.getDescription());
        System.out.println(ex);
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UsernameInvalidInputException.class)
    public ResponseEntity<ExceptionResponse> usernameInvalidInputException(UsernameInvalidInputException ex){
        ExceptionResponse response = new ExceptionResponse();
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setDescription(ex.getMessage());
        logger.error(response.getDescription());
        System.out.println(ex);
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UsernameAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> usernameAlreadyExistException (UsernameAlreadyExistException ex){
        ExceptionResponse response = new ExceptionResponse();
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setDescription(ex.getMessage());
        logger.error(response.getDescription());
        System.out.println(ex);
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EikAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> eikAlreadyExistException (EikAlreadyExistException ex){
        ExceptionResponse response = new ExceptionResponse();
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setDescription(ex.getMessage());
        logger.error(response.getDescription());
        System.out.println(ex);
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UserDoesNotExistException.class)
    public ResponseEntity<ExceptionResponse> userDoesNotExistException (UserDoesNotExistException ex){
        ExceptionResponse response = new ExceptionResponse();
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setDescription(ex.getMessage());
        logger.error(response.getDescription());
        System.out.println(ex);
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ServiceDoesNotExistException.class)
    public ResponseEntity<ExceptionResponse> serviceDoesNotExistException (ServiceDoesNotExistException ex){
        ExceptionResponse response = new ExceptionResponse();
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setDescription(ex.getMessage());
        logger.error(response.getDescription());
        System.out.println(ex);
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = CurrencyDoesNotExistException.class)
    public ResponseEntity<ExceptionResponse> currencyDoesNotExistException (CurrencyDoesNotExistException ex){
        ExceptionResponse response = new ExceptionResponse();
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setDescription(ex.getMessage());
        logger.error(response.getDescription());
        System.out.println(ex);
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = RequestCantBeProcessedException.class)
    public ResponseEntity<ExceptionResponse> requestCantBeProcessedException (RequestCantBeProcessedException ex){
        ExceptionResponse response = new ExceptionResponse();
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setDescription(ex.getMessage());
        logger.error(response.getDescription());
        System.out.println(ex);
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = InvalidTimePeriodException.class)
    public ResponseEntity<ExceptionResponse> invalidTimePeriodException (InvalidTimePeriodException ex){
        ExceptionResponse response = new ExceptionResponse();
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setDescription(ex.getMessage());
        logger.error(response.getDescription());
        System.out.println(ex);
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
