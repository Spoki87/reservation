package com.reservation.reservation.repository;

import com.reservation.reservation.model.Reservation;
import com.reservation.reservation.model.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query("""
    SELECT r FROM Reservation r
    JOIN r.rooms room
    WHERE room.id = :roomId
      AND r.checkOut > :checkIn
      AND r.checkIn < :checkOut
""")
    List<Reservation> findOverlappingReservations(
            @Param("roomId") UUID roomId,
            @Param("checkIn") LocalDate checkIn,
            @Param("checkOut") LocalDate checkOut
    );

}
