package com.reservation.reservation.dto.response;

import com.reservation.hotel.dto.response.RoomShortResponse;
import com.reservation.reservation.model.Reservation;
import com.reservation.reservation.model.ReservationStatus;
import lombok.Value;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Value
public class ReservationResponse {
    UUID id;
    long reservationNumber;
    LocalDate checkIn;
    LocalDate checkOut;
    ReservationStatus status;
    String firstName;
    String lastName;
    String email;
    String phone;
    List<RoomShortResponse> rooms;

    public static ReservationResponse from(Reservation reservation) {
        List<RoomShortResponse> rooms = (reservation.getRooms() != null) ?
                reservation.getRooms().stream()
                        .map(RoomShortResponse::from)
                        .toList():
                new ArrayList<>();

        return new ReservationResponse(
                reservation.getId(),
                reservation.getReservationNumber(),
                reservation.getCheckIn(),
                reservation.getCheckOut(),
                reservation.getStatus(),
                reservation.getFirstName(),
                reservation.getLastName(),
                reservation.getEmail(),
                reservation.getPhone(),
                rooms
        );
    }

}
