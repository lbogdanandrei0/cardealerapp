package com.lbogdanandrei.cardealerapp.controller;

import com.lbogdanandrei.cardealerapp.exceptions.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = DealerNotFoundException.class)
    protected ResponseEntity<Object> handleDealerNotFound(RuntimeException ex, WebRequest request){
        return handleExceptionInternal(ex, "Dealer was not found", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = DealerAlreadyExistsException.class)
    protected ResponseEntity<Object> handleDealerAlreadyExists(RuntimeException ex, WebRequest request){
        return handleExceptionInternal(ex, "Dealer already exists", new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = CarAlreadyExistsException.class)
    protected ResponseEntity<Object> handleCarAlreadyExists(RuntimeException ex, WebRequest request){
        return handleExceptionInternal(ex, "This car already exists", new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = InvalidTokenException.class)
    protected ResponseEntity<Object> handleInvalidToken(RuntimeException ex, WebRequest request){
        return handleExceptionInternal(ex, "Invalid token, activation failed", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = UserNotFoundException.class)
    protected ResponseEntity<Object> handleUserNotFound(RuntimeException ex, WebRequest request){
        return handleExceptionInternal(ex, "User was not activated (invalid email or already activated)", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = UserAlreadyExistException.class)
    protected ResponseEntity<Object> handleUserAlreadyExists(RuntimeException ex, WebRequest request){
        return handleExceptionInternal(ex, "User already exists", new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
