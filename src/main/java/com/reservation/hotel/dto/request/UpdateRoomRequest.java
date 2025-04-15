package com.reservation.hotel.dto.request;

import com.reservation.hotel.model.RoomType;
import jakarta.validation.constraints.*;
import lombok.Value;

@Value
public class UpdateRoomRequest {
    String number;

    @Size(message = "Description limit characters is 500", max = 500)
    String description;

    RoomType type;

    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    Double pricePerDay;

    @Min(value = 1, message = "Room capacity must be greater than 0")
    int capacity;
}
