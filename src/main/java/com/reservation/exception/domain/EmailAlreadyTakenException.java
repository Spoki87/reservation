package com.reservation.exception.domain;

import com.reservation.exception.base.BusinessException;

public class EmailAlreadyTakenException extends BusinessException {
    public EmailAlreadyTakenException() {
        super("Email already taken");
    }
}
