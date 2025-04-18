package com.reservation.reservation.controller;

import com.reservation.reservation.dto.request.CreateReservationRequest;
import com.reservation.reservation.dto.response.ReservationResponse;
import com.reservation.reservation.service.ReservationService;
import com.reservation.response.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/reservations")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminReservationController {

    private final ReservationService reservationService;

    @PostMapping()
    public ResponseEntity<Response<ReservationResponse>> createReservation(@Valid CreateReservationRequest request) {
        ReservationResponse createdReservation = reservationService.create(request);
        return ResponseEntity.ok(Response.success("Created successfully",createdReservation, HttpStatus.CREATED));
    }
}
