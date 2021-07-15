package com.lbogdanandrei.cardealerapp.exceptions;

public class DealerAlreadyExistsException extends RuntimeException{

    public DealerAlreadyExistsException(){
        super("Dealer already exist");
    }
}
