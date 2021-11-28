package com.nelisriebezos.broozercruiserbot.Exceptions;

public class NegativeMileageException extends RuntimeException{
    public NegativeMileageException(String message) {
        super(message);
    }
}
