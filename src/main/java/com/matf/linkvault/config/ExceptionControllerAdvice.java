package com.matf.linkvault.config;


import com.matf.linkvault.exceptions.IncorrectEmailOrPasswordException;
import com.matf.linkvault.exceptions.UserAlreadyExistsException;
import com.matf.linkvault.exceptions.UserNotFoundException;
import com.matf.linkvault.models.responses.AppResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler({UserAlreadyExistsException.class})
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public AppResponse handleUserAlreadyExistsException(UserAlreadyExistsException exception) {

        log.error("User already exists ", exception);
        return AppResponseUtil.buildErrorResponse("User already exists " + exception.getMessage());
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AppResponse handleDataIntegrityViolationException(DataIntegrityViolationException exception) {

        log.error(String.valueOf(exception));
        return AppResponseUtil.buildErrorResponse(exception.getMessage());
    }

    @ExceptionHandler({IncorrectEmailOrPasswordException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AppResponse handleIncorrectUsernameOrPasswordException(IncorrectEmailOrPasswordException exception) {

        log.error(String.valueOf(exception));
        return AppResponseUtil.buildErrorResponse(exception.getMessage());
    }

    @ExceptionHandler({UserNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AppResponse handleUserNotFoundException(UserNotFoundException exception) {

        log.error(String.valueOf(exception));
        return AppResponseUtil.buildErrorResponse(exception.getMessage());
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AppResponse handleWildCardException(Exception exception) {

        log.error(String.valueOf(exception));
        return AppResponseUtil.buildErrorResponse(exception.getMessage());
    }
}
