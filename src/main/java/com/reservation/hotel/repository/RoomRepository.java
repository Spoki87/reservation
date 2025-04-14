package com.reservation.hotel.repository;

import com.reservation.hotel.model.Room;
import com.reservation.hotel.model.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<Room, UUID> {
    Optional<Room> findByNumber(String number);
    List<Room> findByStatus(RoomStatus status);
    List<Room> findByStatusNot(RoomStatus status);


}
