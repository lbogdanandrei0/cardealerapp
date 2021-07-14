package com.lbogdanandrei.cardealerapp.exceptions;

public class DealerNotFoundException extends Exception{

    public DealerNotFoundException(){
        super("Dealer was not found");
    }
}
