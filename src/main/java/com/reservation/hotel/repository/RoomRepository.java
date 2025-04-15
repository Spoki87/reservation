package com.reservation.hotel.repository;

import com.reservation.hotel.model.Room;
import com.reservation.hotel.model.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<Room, UUID> {
    Optional<Room> findByNumber(String number);
    List<Room> findByStatus(RoomStatus status);
    List<Room> findByStatusNot(RoomStatus status);

    @Query("""
    SELECT r FROM Room r
    WHERE r.status = 'AVAILABLE'
    AND r NOT IN (
        SELECT rr FROM Reservation res
        JOIN res.rooms rr
        WHERE NOT (res.checkOut < :from OR res.checkIn > :to)
    )
    """)
    List<Room> findAvailableRoomsInDateRange(LocalDate from, LocalDate to);

    @Query("""
    SELECT DISTINCT r FROM Room r
    WHERE r.status != 'AVAILABLE'
    OR r IN (
        SELECT rr FROM Reservation res
        JOIN res.rooms rr
        WHERE NOT (res.checkOut < :from OR res.checkIn > :to)
    )
    """)
    List<Room> findUnavailableRoomsInDateRange(LocalDate from, LocalDate to);

}
