package com.reservation.reservation.service;

import com.reservation.reservation.dto.response.ReservationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationEventProducer {

    private final KafkaTemplate<String, ReservationResponse> kafkaTemplate;

    public void sendReservationConfirmed(ReservationResponse reservationResponse) {
        kafkaTemplate.send("reservation-confirmed", reservationResponse);
    }

    public void sendReservationCancelled(ReservationResponse reservationResponse) {
        kafkaTemplate.send("reservation-cancelled", reservationResponse);
    }
}
