package com.rohit.week4ProductionTutorials.Spring_Boot_Week_4.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message){
        super(message);
    }
}
