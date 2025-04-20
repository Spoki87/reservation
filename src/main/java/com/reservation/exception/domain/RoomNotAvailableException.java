package com.reservation.exception.domain;

import com.reservation.exception.base.BusinessException;
import com.reservation.hotel.model.Room;

public class RoomNotAvailableException extends BusinessException {
    public RoomNotAvailableException(Room room) {
        super("Room {room.id=" + room.getId() + "} is not available");
    }
}
