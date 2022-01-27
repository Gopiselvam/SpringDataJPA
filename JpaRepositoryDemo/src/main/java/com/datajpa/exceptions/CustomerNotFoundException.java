package com.datajpa.exceptions;

public class CustomerNotFoundException extends Exception{

    public CustomerNotFoundException(String msg) {
        super(msg);
    }
}
