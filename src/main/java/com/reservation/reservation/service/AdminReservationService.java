package com.reservation.reservation.service;

import com.reservation.email.EmailSender;
import com.reservation.exception.domain.ResourceNotFoundException;
import com.reservation.reservation.ReservationEmailTemplateBuilder;
import com.reservation.reservation.dto.request.UpdateReservationRequest;
import com.reservation.reservation.dto.response.ReservationResponse;
import com.reservation.reservation.model.Reservation;
import com.reservation.reservation.model.ReservationStatus;
import com.reservation.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminReservationService {
    private final ReservationRepository reservationRepository;
    private final EmailSender emailSender;
    private final ReservationEmailTemplateBuilder reservationEmailTemplateBuilder;
    private final ReservationEventProducer reservationEventProducer;

    public ReservationResponse findById(UUID id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        return ReservationResponse.from(reservation);
    }

    public List<ReservationResponse> findAll() {
        return reservationRepository.findAll().stream().map(ReservationResponse::from).toList();
    }

    public List<ReservationResponse> findByStatus(ReservationStatus status) {
        return reservationRepository.findByStatus(status).stream().map(ReservationResponse::from).toList();
    }

    public List<ReservationResponse> findByDatesAndStatus(LocalDate from, LocalDate to, ReservationStatus status) {
        return reservationRepository.findByDatesAndStatus(from,to,status).stream().map(ReservationResponse::from).toList();
    }

    @Transactional
    public ReservationResponse updateReservationContactInformation(UUID id, UpdateReservationRequest request){
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        reservation.changeContactInformation(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPhone()
        );
        return ReservationResponse.from(reservation);
    }

    @Transactional
    public void cancelReservationById(UUID id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        reservation.cancelReservation();

        String html = reservationEmailTemplateBuilder.buildCancellationEmail(
                reservation.getFirstName(),
                reservation.getReservationNumber()
        );
        emailSender.send(reservation.getEmail(),html,"Reservation cancelled");
        reservationEventProducer.sendReservationCancelled(ReservationResponse.from(reservation));

    }
}
