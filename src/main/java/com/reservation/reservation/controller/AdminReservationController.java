package com.reservation.reservation.controller;

import com.reservation.reservation.dto.request.UpdateReservationRequest;
import com.reservation.reservation.dto.response.ReservationResponse;
import com.reservation.reservation.model.ReservationStatus;
import com.reservation.reservation.service.AdminReservationService;
import com.reservation.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Tag(name = "Reservations management", description = "Endpoints available with admin authentication")
@RestController
@RequestMapping("/api/admin/reservations")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminReservationController {

    private final AdminReservationService reservationService;

    @Operation(
            summary = "Fetch reservation by id"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Response<ReservationResponse>> getReservationById(@PathVariable UUID id) {
        ReservationResponse response = reservationService.findById(id);
        return ResponseEntity.ok(Response.success("Found successfully",response, HttpStatus.OK));
    }

    @Operation(
            summary = "Fetch all reservations"
    )
    @GetMapping()
    public ResponseEntity<Response<List<ReservationResponse>>> getAllReservations() {
        List<ReservationResponse> reservations = reservationService.findAll();
        return ResponseEntity.ok(Response.success("Found successfully",reservations,HttpStatus.OK));
    }

    @Operation(
            summary = "Fetch reservations by status"
    )
    @GetMapping("/by-status")
    public ResponseEntity<Response<List<ReservationResponse>>> getReservationsByStatus(@RequestParam ReservationStatus status) {
        List<ReservationResponse> reservations = reservationService.findByStatus(status);
        return ResponseEntity.ok(Response.success("Found successfully",reservations,HttpStatus.OK));
    }

    @Operation(
            summary = "Fetch reservations by date range and reservation status"
    )
    @GetMapping("/by-dates")
    public ResponseEntity<Response<List<ReservationResponse>>> getReservationsByDatesAndStatus(@RequestParam ReservationStatus status, @RequestParam LocalDate from, @RequestParam LocalDate to) {
        List<ReservationResponse> reservations = reservationService.findByDatesAndStatus(from,to,status);
        return ResponseEntity.ok(Response.success("Found successfully",reservations,HttpStatus.OK));
    }

    @Operation(
            summary = "Update contact information"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Response<ReservationResponse>> updateContactInformation(@PathVariable UUID id, @RequestBody @Valid UpdateReservationRequest request) {
        ReservationResponse response = reservationService.updateReservationContactInformation(id,request);
        return ResponseEntity.ok(Response.success("Updated successfully",response,HttpStatus.OK));
    }

    @Operation(
            summary = "Cancel reservation by id"
    )
    @PostMapping("/cancel/{id}")
    public ResponseEntity<Response<String>> cancelReservation(@PathVariable UUID id) {
        reservationService.cancelReservationById(id);
        return ResponseEntity.ok(Response.success("Cancelled successfully",null,HttpStatus.OK));
    }
}
