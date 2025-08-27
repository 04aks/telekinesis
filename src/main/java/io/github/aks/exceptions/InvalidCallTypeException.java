package io.github.aks.exceptions;

public class InvalidCallTypeException extends Exception {
    public InvalidCallTypeException(String callType){
        super("Invalid call type: " + callType.toUpperCase());
    }    
}
