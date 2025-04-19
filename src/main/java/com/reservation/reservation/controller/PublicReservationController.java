package com.reservation.reservation.controller;

import com.reservation.reservation.dto.request.CreateReservationRequest;
import com.reservation.reservation.dto.response.ReservationResponse;
import com.reservation.reservation.service.ReservationService;
import com.reservation.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Reservations", description = "Endpoints available without authentication")
@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class PublicReservationController {
    private final ReservationService reservationService;

    @Operation(
            summary = "Create new reservation"
    )
    @PostMapping()
    public ResponseEntity<Response<ReservationResponse>> createReservation(@Valid CreateReservationRequest request) {
        ReservationResponse createdReservation = reservationService.create(request);
        return ResponseEntity.ok(Response.success("Created successfully",createdReservation, HttpStatus.CREATED));
    }

    @Operation(
            summary = "Fetch reservation by number"
    )
    @GetMapping("/{number}")
    public ResponseEntity<Response<ReservationResponse>> getReservation(@PathVariable long number) {
        ReservationResponse response = reservationService.findByReservationNumber(number);
        return ResponseEntity.ok(Response.success("Found successfully",response, HttpStatus.OK));
    }

    @Operation(
            summary = "Cancel reservation by number"
    )
    @PostMapping("/cancel/{number}")
    public ResponseEntity<Response<String>> cancelReservation(@PathVariable long number) {
        reservationService.cancelReservationByNumber(number);
        return ResponseEntity.ok(Response.success("Canceled successfully",null, HttpStatus.OK));
    }
}
