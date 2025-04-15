package com.reservation.exception.domain;

import com.reservation.exception.base.BusinessException;

public class RoomIllegalException extends BusinessException {
    public RoomIllegalException(String message) {
        super(message);
    }
}
