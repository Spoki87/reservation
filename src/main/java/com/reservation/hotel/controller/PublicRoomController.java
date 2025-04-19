package com.reservation.hotel.controller;

import com.reservation.hotel.dto.response.RoomResponse;
import com.reservation.hotel.service.PublicRoomService;
import com.reservation.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Rooms", description = "Endpoints available without authentication")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rooms")
public class PublicRoomController {

    private final PublicRoomService roomService;

    @Operation(
            summary = "Fetch available rooms"
    )
    @GetMapping("/available")
    public ResponseEntity<Response<List<RoomResponse>>> getAvailableRooms() {
        List<RoomResponse> response = roomService.getAvailableRooms();
        return ResponseEntity.ok(Response.success("fetch successfully", response, HttpStatus.OK));
    }

    @Operation(
            summary = "Fetch available rooms in a date range",
            description = "Fetch available rooms in a specific date range"
    )
    @GetMapping("/available/by-dates")
    public ResponseEntity<Response<List<RoomResponse>>> getAvailableRoomsByDates(@RequestParam("from") LocalDate from, @RequestParam("to") LocalDate to) {
        List<RoomResponse> response = roomService.getAvailableRoomsByDates(from, to);
        return ResponseEntity.ok(Response.success("fetch successfully", response, HttpStatus.OK));
    }
}
