package com.reservation.hotel.controller;

import com.reservation.hotel.dto.response.RoomResponse;
import com.reservation.hotel.service.PublicRoomService;
import com.reservation.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rooms")
public class PublicRoomController {

    private final PublicRoomService roomService;

    @GetMapping("/available")
    public ResponseEntity<Response<List<RoomResponse>>> getAvailableRooms() {
        List<RoomResponse> response = roomService.getAvailableRooms();
        return ResponseEntity.ok(Response.success("fetch successfully", response, HttpStatus.OK));
    }

    @GetMapping("/available/by-dates")
    public ResponseEntity<Response<List<RoomResponse>>> getAvailableRoomsByDates(@RequestParam("from") LocalDate from, @RequestParam("to") LocalDate to) {
        List<RoomResponse> response = roomService.getAvailableRoomsByDates(from, to);
        return ResponseEntity.ok(Response.success("fetch successfully", response, HttpStatus.OK));
    }
}
