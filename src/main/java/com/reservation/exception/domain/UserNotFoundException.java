package com.reservation.exception.domain;

import com.reservation.exception.base.BusinessException;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException() {
        super("User not found");
    }
}