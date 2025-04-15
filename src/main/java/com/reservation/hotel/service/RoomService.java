package com.reservation.hotel.service;

import com.reservation.exception.domain.ResourceNotFoundException;
import com.reservation.exception.domain.RoomIllegalException;
import com.reservation.hotel.dto.request.CreateRoomRequest;
import com.reservation.hotel.dto.request.UpdateRoomRequest;
import com.reservation.hotel.dto.response.RoomResponse;
import com.reservation.hotel.model.Room;
import com.reservation.hotel.model.RoomStatus;
import com.reservation.hotel.repository.RoomRepository;
import com.reservation.reservation.model.ReservationStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public List<RoomResponse> getRooms() {
        return roomRepository.findAll().stream()
                .map(RoomResponse::from)
                .toList();
    }

    public RoomResponse getRoomById(UUID id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        return RoomResponse.from(room);
    }

    public RoomResponse getRoomByNumber(String number){
        Room room = roomRepository.findByNumber(number)
                .orElseThrow(ResourceNotFoundException::new);
        return RoomResponse.from(room);
    }

    public List<RoomResponse> getAvailableRooms(){
        return roomRepository.findByStatus(RoomStatus.AVAILABLE).stream()
                .map(RoomResponse::from)
                .toList();
    }

    public List<RoomResponse> getUnavailableRooms(){
        return roomRepository.findByStatusNot(RoomStatus.AVAILABLE).stream()
                .map(RoomResponse::from)
                .toList();
    }

    public List<RoomResponse> getAvailableRoomsByDates(LocalDate from, LocalDate to){
        return roomRepository.findAvailableRoomsInDateRange(from,to).stream()
                .map(RoomResponse::from)
                .toList();
    }

    public List<RoomResponse> getUnavailableRoomsByDates(LocalDate from, LocalDate to){
        return roomRepository.findUnavailableRoomsInDateRange(from,to).stream()
                .map(RoomResponse::from)
                .toList();
    }

    public RoomResponse createRoom(CreateRoomRequest request){
        Room room = new Room(
                request.getNumber(),
                request.getDescription(),
                request.getType(),
                request.getPricePerDay(),
                request.getCapacity()
                );
        roomRepository.save(room);

        return RoomResponse.from(room);
    }

    @Transactional
    public RoomResponse updateRoom(UUID id, UpdateRoomRequest request){
        Room room = roomRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        if (StringUtils.hasText(request.getNumber())) {
            room.changeNumber(request.getNumber());
        }

        if (StringUtils.hasText(request.getDescription())) {
            room.changeDescription(request.getDescription());
        }

        if (request.getType() != null) {
            room.changeType(request.getType());
        }

        if (request.getPricePerDay() != null ) {
            room.changePricePerDay(request.getPricePerDay());
        }

        if (request.getCapacity() > 0) {
            room.changeCapacity(request.getCapacity());
        }

        return RoomResponse.from(room);
    }

    @Transactional
    public void deleteRoom(UUID id){
        Room room = roomRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        if(room.isReserved()){
            throw new RoomIllegalException("Can not delete this room, because it is reserved");
        }

        if(room.getReservations().stream().anyMatch(reservation -> reservation.getStatus() == ReservationStatus.CONFIRMED)){
            throw new RoomIllegalException("Can not delete this room, because it has confirmed reservations");
        }

        roomRepository.delete(room);
    }
}
