package com.reservation.reservation.service;

import com.reservation.email.EmailSender;
import com.reservation.exception.domain.ResourceNotFoundException;
import com.reservation.hotel.model.Room;
import com.reservation.reservation.ReservationEmailTemplateBuilder;
import com.reservation.reservation.dto.request.CreateReservationRequest;
import com.reservation.reservation.dto.response.ReservationResponse;
import com.reservation.reservation.model.Reservation;
import com.reservation.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationEmailTemplateBuilder reservationEmailTemplateBuilder;
    private final EmailSender emailSender;
    private final ReservationEventProducer reservationEventProducer;

    public ReservationResponse create(CreateReservationRequest createReservationRequest) {

        Reservation reservation = new Reservation(
                createReservationRequest.getCheckIn(),
                createReservationRequest.getCheckOut(),
                createReservationRequest.getFirstName(),
                createReservationRequest.getLastName(),
                createReservationRequest.getEmail(),
                createReservationRequest.getPhone()
        );

        reservationRepository.save(reservation);

        String html = reservationEmailTemplateBuilder.buildConfirmationEmail(
                reservation.getFirstName(),
                reservation.getLastName(),
                reservation.getReservationNumber(),
                reservation.getCheckIn(),
                reservation.getCheckOut(),
                reservation.getRooms().stream().map(Room::getNumber).toList()
        );

        emailSender.send(reservation.getEmail(),html,"Reservation confirmation");
        reservationEventProducer.sendReservationConfirmed(ReservationResponse.from(reservation));

        return ReservationResponse.from(reservation);
    }

    public ReservationResponse findByReservationNumber(long reservationNumber) {
        Reservation reservation = reservationRepository.findByReservationNumber(reservationNumber)
                .orElseThrow(ResourceNotFoundException::new);
        return ReservationResponse.from(reservation);
    }

    @Transactional
    public void cancelReservationByNumber(long number) {
        Reservation reservation = reservationRepository.findByReservationNumber(number)
                .orElseThrow(ResourceNotFoundException::new);
        reservation.cancelReservation();
        reservationEventProducer.sendReservationCancelled(ReservationResponse.from(reservation));
    }

}
