package com.reservation.reservation.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class UpdateReservationRequest {
    @NotNull(message = "First name is required")
    String firstName;

    @NotNull(message = "Last name is required")
    String lastName;

    @NotNull(message = "Email is required")
    @Email(message = "Email must be valid")
    String email;

    @NotNull(message = "Phone is required")
    String phone;
}
