package com.lbogdanandrei.cardealerapp.controller;

import com.lbogdanandrei.cardealerapp.exceptions.DealerNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = DealerNotFoundException.class)
    protected ResponseEntity<Object> handleDealerNotFound(RuntimeException ex, WebRequest request){
        return handleExceptionInternal(ex, "Dealer was not found", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
