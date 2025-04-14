package com.reservation.exception.domain;

import com.reservation.exception.base.BusinessException;

public class ResourceNotFoundException extends BusinessException {
    public ResourceNotFoundException() {
        super("Resource not found");
    }
}
