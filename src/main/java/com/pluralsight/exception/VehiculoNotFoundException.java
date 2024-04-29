package com.pluralsight.exception;

public class VehiculoNotFoundException extends RuntimeException {

    public VehiculoNotFoundException(String exception){
        super(exception);
    }
}
