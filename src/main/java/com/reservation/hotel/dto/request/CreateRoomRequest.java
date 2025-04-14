package com.reservation.hotel.dto.request;

import com.reservation.hotel.model.RoomType;
import jakarta.validation.constraints.*;
import lombok.Value;

@Value
public class CreateRoomRequest {
    @NotBlank(message = "number is required")
    String number;

    @Size(message = "Description limit characters is 500", max = 500)
    String description;

    @NotNull(message = "Room type is required")
    RoomType type;

    @NotNull(message = "Price per day is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    double pricePerDay;

    @NotNull(message = "Room capacity is required")
    @Min(value = 1, message = "Room capacity must be greater than 0")
    int capacity;
}
