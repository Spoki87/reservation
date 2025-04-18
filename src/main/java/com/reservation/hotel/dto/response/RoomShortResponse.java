package com.reservation.hotel.dto.response;

import com.reservation.hotel.model.Room;
import lombok.Value;

import java.util.UUID;

@Value
public class RoomShortResponse {
    UUID id;
    String number;

    public static RoomShortResponse from(Room room) {
        return new RoomShortResponse(
                room.getId(),
                room.getNumber()
        );
    }
}
