package com.reservation.reservation.dto.response;

import com.reservation.reservation.model.Reservation;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
public class ReservationShortResponse {
    UUID id;
    LocalDate checkInDate;
    LocalDate checkOutDate;

    public static ReservationShortResponse from(Reservation reservation) {

        return new ReservationShortResponse(
                reservation.getId(),
                reservation.getCheckIn(),
                reservation.getCheckOut()
        );
    }
}
