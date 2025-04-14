package com.reservation.reservation.dto.response;

import com.reservation.reservation.model.Reservation;

import java.time.LocalDate;

public class ReservationShortResponse {
    private Long id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public static ReservationShortResponse from(Reservation reservation) {
        return null; //to do
    }
}
