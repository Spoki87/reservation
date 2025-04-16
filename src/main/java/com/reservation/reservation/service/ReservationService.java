package com.reservation.reservation.service;

import com.reservation.reservation.dto.request.CreateReservationRequest;
import com.reservation.reservation.dto.response.ReservationResponse;
import com.reservation.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationResponse create(CreateReservationRequest createReservationRequest) {
        return null;
    }
}
