package com.reservation.hotel.dto.response;

import com.reservation.hotel.model.Room;
import com.reservation.hotel.model.RoomStatus;
import com.reservation.hotel.model.RoomType;
import com.reservation.reservation.dto.response.ReservationShortResponse;
import com.reservation.reservation.model.ReservationStatus;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
public class RoomResponse {
    UUID id;
    String number;
    String description;
    RoomType roomType;
    int capacity;
    double pricePerDay;
    RoomStatus status;
    List<ReservationShortResponse> reservations;

    public static RoomResponse from(Room room) {
        List<ReservationShortResponse> activeReservations = room.getReservations().stream()
                .filter(res -> res.getStatus() == ReservationStatus.CONFIRMED)
                .map(ReservationShortResponse::from)
                .toList();

        return new RoomResponse(
                room.getId(),
                room.getNumber(),
                room.getDescription(),
                room.getType(),
                room.getCapacity(),
                room.getPricePerDay(),
                room.getStatus(),
                activeReservations
        );
    }
}
