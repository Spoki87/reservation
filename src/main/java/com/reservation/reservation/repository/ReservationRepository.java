package com.reservation.reservation.repository;

import com.reservation.reservation.model.Reservation;
import com.reservation.reservation.model.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    public List<Reservation> findByStatus(ReservationStatus status);

    @Query("SELECT r FROM Reservation r WHERE r.checkIn >= :from AND r.checkOut <= :to AND r.status = :status")
    public List<Reservation> findByDatesAndStatus(LocalDate from, LocalDate to, ReservationStatus status);

    Optional<Reservation> findByReservationNumber(Long reservationNumber);
}
