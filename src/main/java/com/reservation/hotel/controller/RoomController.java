package com.reservation.hotel.controller;

import com.reservation.hotel.dto.request.CreateRoomRequest;
import com.reservation.hotel.dto.request.UpdateRoomRequest;
import com.reservation.hotel.dto.response.RoomResponse;
import com.reservation.hotel.service.RoomService;
import com.reservation.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/rooms")
@PreAuthorize("hasRole('ADMIN')")
public class RoomController {

    private final RoomService roomService;

    @PostMapping()
    ResponseEntity<Response<RoomResponse>> createRoom(CreateRoomRequest request) {
        RoomResponse response = roomService.createRoom(request);
        return ResponseEntity.ok(Response.success("created successfully", response, HttpStatus.CREATED));
    }

    @PutMapping("/{id}")
    ResponseEntity<Response<RoomResponse>> updateRoom(@PathVariable UUID id, UpdateRoomRequest request) {
        RoomResponse response = roomService.updateRoom(id, request);
        return ResponseEntity.ok(Response.success("updated successfully", response, HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Response<String>> deleteRoom(@PathVariable UUID id) {
        roomService.deleteRoom(id);
        return ResponseEntity.ok(Response.success("deleted successfully", null, HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<RoomResponse>> getRoomById(@PathVariable UUID id) {
        RoomResponse response = roomService.getRoomById(id);
        return ResponseEntity.ok(Response.success("fetch successfully", response, HttpStatus.OK));
    }

    @GetMapping()
    ResponseEntity<Response<List<RoomResponse>>> getAllRooms() {
        List<RoomResponse> response = roomService.getRooms();
        return ResponseEntity.ok(Response.success("fetch successfully", response, HttpStatus.OK));
    }

    @GetMapping("/number/{number}")
    public ResponseEntity<Response<RoomResponse>> getRoomByNumber(@PathVariable String number) {
        RoomResponse response = roomService.getRoomByNumber(number);
        return ResponseEntity.ok(Response.success("fetch successfully", response, HttpStatus.OK));
    }

    @GetMapping("/unavailable")
    public ResponseEntity<Response<List<RoomResponse>>> getUnavailableRooms() {
        List<RoomResponse> response = roomService.getUnavailableRooms();
        return ResponseEntity.ok(Response.success("fetch successfully", response, HttpStatus.OK));
    }

    @GetMapping("/unavailable/by-dates")
    public ResponseEntity<Response<List<RoomResponse>>> getUnavailableRoomsByDates(@RequestParam("from") LocalDate from, @RequestParam("to") LocalDate to) {
        List<RoomResponse> response = roomService.getUnavailableRoomsByDates(from, to);
        return ResponseEntity.ok(Response.success("fetch successfully", response, HttpStatus.OK));
    }

}
