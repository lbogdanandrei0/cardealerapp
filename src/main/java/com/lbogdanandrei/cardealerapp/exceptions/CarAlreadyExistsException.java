package com.lbogdanandrei.cardealerapp.exceptions;

public class CarAlreadyExistsException extends RuntimeException{

    public CarAlreadyExistsException(){
        super("This car is already registered");
    }
}
