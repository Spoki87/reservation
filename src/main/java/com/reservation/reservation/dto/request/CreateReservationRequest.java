package com.reservation.reservation.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
public class CreateReservationRequest {

    @NotNull(message = "Check-in date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate checkIn;

    @NotNull(message = "Check-out date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate checkOut;

    @NotNull(message = "First name is required")
    String firstName;

    @NotNull(message = "Last name is required")
    String lastName;

    @NotNull(message = "Email is required")
    @Email(message = "Email must be valid")
    String email;

    @NotNull(message = "Phone is required")
    String phone;

    @NotEmpty(message = "Room numbers list must contain at least one room number")
    List<String> roomNumbers;
}
