package com.lbogdanandrei.cardealerapp.exceptions;

public class DealerNotFoundException extends RuntimeException{

    public DealerNotFoundException(){
        super("Dealer was not found");
    }
}
