package com.reservation.exception.base;

public class BusinessException extends RuntimeException{
    public BusinessException(String message) {
        super(message);
    }
}
