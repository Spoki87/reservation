package com.reservation.reservation.controller;

import com.reservation.reservation.dto.request.UpdateReservationRequest;
import com.reservation.reservation.dto.response.ReservationResponse;
import com.reservation.reservation.model.ReservationStatus;
import com.reservation.reservation.service.AdminReservationService;
import com.reservation.response.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/reservations")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminReservationController {

    private final AdminReservationService reservationService;

    @GetMapping("/{id}")
    public ResponseEntity<Response<ReservationResponse>> getReservationById(@PathVariable UUID id) {
        ReservationResponse response = reservationService.findById(id);
        return ResponseEntity.ok(Response.success("Found successfully",response, HttpStatus.OK));
    }

    @GetMapping()
    public ResponseEntity<Response<List<ReservationResponse>>> getAllReservations() {
        List<ReservationResponse> reservations = reservationService.findAll();
        return ResponseEntity.ok(Response.success("Found successfully",reservations,HttpStatus.OK));
    }

    @GetMapping("/by-status")
    public ResponseEntity<Response<List<ReservationResponse>>> getReservationsByStatus(@RequestParam ReservationStatus status) {
        List<ReservationResponse> reservations = reservationService.findByStatus(status);
        return ResponseEntity.ok(Response.success("Found successfully",reservations,HttpStatus.OK));
    }

    @GetMapping("/by-dates")
    public ResponseEntity<Response<List<ReservationResponse>>> getReservationsByDatesAndStatus(@RequestParam ReservationStatus status, @RequestParam LocalDate from, @RequestParam LocalDate to) {
        List<ReservationResponse> reservations = reservationService.findByDatesAndStatus(from,to,status);
        return ResponseEntity.ok(Response.success("Found successfully",reservations,HttpStatus.OK));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<ReservationResponse>> updateContactInformation(@PathVariable UUID id, @RequestBody @Valid UpdateReservationRequest request) {
        ReservationResponse response = reservationService.updateReservationContactInformation(id,request);
        return ResponseEntity.ok(Response.success("Updated successfully",response,HttpStatus.OK));
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<Response<String>> cancelReservation(@PathVariable UUID id) {
        reservationService.cancelReservationById(id);
        return ResponseEntity.ok(Response.success("Cancelled successfully",null,HttpStatus.OK));
    }
}
